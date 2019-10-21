package com.baizhi.service;

import com.baizhi.Repository.CustomerRepository;
import com.baizhi.entity.Article;
import com.baizhi.mapper.ArticleMapper;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    CustomerRepository customerRepository;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        Integer count = articleMapper.count();
        Integer stats = (page - 1) * rows;
        Integer total = count % rows == 0 ? count / rows : count / rows + 1;
        List<Article> all = articleMapper.queryAll(stats, rows);
        Map<String, Object> maps = new HashMap<>();
        maps.put("rows", all);
        maps.put("records", count);
        maps.put("page", page);
        maps.put("total", total);
        return maps;
    }

    @Override
    public List<Article> query(String val) {
        List<Article> all = articleMapper.findAll();
        for (Article article : all) {
            customerRepository.save(article);
        }
        HighlightBuilder.Field field = new HighlightBuilder.Field("*");
        field.preTags("<span style='color:red'>");
        field.postTags("</span>");
        field.requireFieldMatch(false);
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withIndices("cmfz")
                .withTypes("es")
                .withQuery(QueryBuilders.queryStringQuery(val).analyzer("ik_max_word").field("title").field("content").field("author"))
                .withHighlightFields(field)
                .build();
        AggregatedPage<Article> articles = elasticsearchTemplate.queryForPage(nativeSearchQuery, Article.class, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
                SearchHits hits = searchResponse.getHits();
                SearchHit[] searchHits = hits.getHits();
                List<Article> list = new ArrayList<>();
                for (SearchHit searchHit : searchHits) {
                    Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
                    Article article = new Article();
                    article.setId(sourceAsMap.get("id").toString());
                    article.setAuthor(sourceAsMap.get("author").toString());
                    //article.setDate(new Date(Long.valueOf(sourceAsMap.get("date").toString())));
                    article.setContent(sourceAsMap.get("content").toString());
                    article.setStatus(sourceAsMap.get("status").toString());
                    article.setTitle(sourceAsMap.get("title").toString());
                    Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
                    if (highlightFields.get("title") != null) {
                        String title = highlightFields.get("title").getFragments()[0].toString();
                        article.setTitle(title);
                    }
                    if (highlightFields.get("content") != null) {
                        String content = highlightFields.get("content").getFragments()[0].toString();
                        article.setContent(content);
                    }
                    if (highlightFields.get("author") != null) {
                        String author = highlightFields.get("author").getFragments()[0].toString();
                        article.setAuthor(author);
                    }
                    list.add(article);
                }
                return new AggregatedPageImpl<T>((List<T>) list);
            }
        });
        return articles.getContent();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Integer count() {
        return articleMapper.count();
    }

    @Override
    public void add(Article article) {
        articleMapper.add(article);
    }

    @Override
    public void update(Article article) {
        articleMapper.update(article);
    }

    @Override
    public void del(String[] id, String oper) {
        if (oper.equals("del")) {
            articleMapper.del(id);
        }
    }
}
