package com.baizhi.service;

import ch.qos.logback.core.util.FileUtil;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.Slideshow;
import com.baizhi.mapper.BannerMapper;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    BannerMapper bannerMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> findAll(Integer page, Integer rows) {
        Integer count = bannerMapper.count();
        Integer stats = (page - 1) * rows;
        Integer total = count % rows == 0 ? count / rows : count / rows + 1;
        List<Slideshow> all = bannerMapper.findAll(stats, rows);
        Map<String, Object> maps = new HashMap<>();
        maps.put("rows", all);
        maps.put("records", count);
        maps.put("page", page);
        maps.put("total", total);
        return maps;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void queryAll(HttpServletRequest request, HttpServletResponse response) {
        String imgPath = request.getServletContext().getRealPath("/img");

        List<Slideshow> list = bannerMapper.queryAll();
        for (Slideshow slideshow : list) {
            slideshow.setSrc(imgPath+"/"+slideshow.getSrc());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("轮播图信息","轮播图"),
                Slideshow .class, list);

        try {
            ServletOutputStream outputStream = response.getOutputStream();
            response.setHeader("content-disposition","attachment");
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            workbook.write(outputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void add(Slideshow slideshow) {
        bannerMapper.add(slideshow);
    }

    @Override
    public void update(Slideshow slideshow) {

        bannerMapper.update(slideshow);
    }

    @Override
    public void updateSrc(String src, String id) {
        bannerMapper.updateSrc(src,id);
    }

    @Override
    public int count() {
        int count = bannerMapper.count();
        return count;
    }

    @Override
    public void delete(String[] id) {
            bannerMapper.delete(id);
    }
}
