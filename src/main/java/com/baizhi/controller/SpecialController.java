package com.baizhi.controller;

import com.baizhi.entity.Special;
import com.baizhi.service.ChapterService;
import com.baizhi.service.SpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("ablum")
public class SpecialController {
    @Autowired
    SpecialService specialService;
    @Autowired
    ChapterService chapterService;
    @RequestMapping("queryAll")
    public Map<String,Object> queryAll(Integer page, Integer rows){
        Map<String, Object> map = specialService.queryAll(page, rows);
        return map;
    }
    @RequestMapping("edit")
    public String edit(Special special,String oper,String[] id){
        if(oper.equals("add")){
            String s = UUID.randomUUID().toString();
            special.setId(s);
            special.setCount(0);
            specialService.add(special);
            return s;
        }else if(oper.equals("del")){
            System.out.println("1111111111111");
            specialService.delChapter(id);
            specialService.del(id);
        }else {
            specialService.update(special);
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

        specialService.updatePath(newFileName,bannerId);

    }
}
