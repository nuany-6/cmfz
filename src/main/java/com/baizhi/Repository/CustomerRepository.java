package com.baizhi.Repository;

import com.baizhi.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CustomerRepository extends ElasticsearchRepository<Article, String> {
}
