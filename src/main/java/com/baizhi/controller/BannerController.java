package com.baizhi.controller;

import com.baizhi.entity.Slideshow;
import com.baizhi.service.BannerService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("banner")
public class BannerController {
    @Autowired
    BannerService bannerService;
    @RequestMapping("findAll")
    public Map<String, Object> findAll(Integer page, Integer rows){
        Map<String, Object> all = bannerService.findAll(page, rows);
        return all;
    }
    @RequestMapping("queryAll")
    public void queryAll(HttpServletRequest request, HttpServletResponse response){
        bannerService.queryAll(request,response);

    }
    @RequestMapping("edit")
    public String edit(Slideshow slideshow ,String oper,String[] id){
        if(oper.equals("add")){
            String s = UUID.randomUUID().toString();
            slideshow.setId(s);
            bannerService.add(slideshow);
            return s;
        }else if(oper.equals("del")){
            bannerService.delete(id);
        }else {
            bannerService.update(slideshow);
        }
        return null;
    }

    @RequestMapping("upload")
    public void upload(MultipartFile src, String bannerId, HttpSession session){
        //获取图片存储的位置
        String realPath = session.getServletContext().getRealPath("/img");
        System.out.println(realPath);
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        String originalFilename = src.getOriginalFilename();
        String newFileName = new Date().getTime() + "_" + originalFilename;

        try {
            src.transferTo(new File(realPath,newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(src);

        bannerService.updateSrc(newFileName,bannerId);

    }
}
