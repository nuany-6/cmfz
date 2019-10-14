package com.baizhi.controller;

import ch.qos.logback.core.util.FileUtil;
import com.baizhi.entity.Chapter;
import com.baizhi.entity.Special;
import com.baizhi.service.ChapterService;

import com.baizhi.service.SpecialService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("chapter")
public class ChapterController {
    @Autowired
    ChapterService chapterService;
    @Autowired
    SpecialService specialService;
    @RequestMapping("findAllPage")
    public Map<String,Object> findAllPage(Integer page,Integer rows,String albumid){
        Map<String, Object> allPage = chapterService.findAllPage(page, rows,albumid);
        return allPage;
    }
    @RequestMapping("edit")
    public String edit( Chapter chapter, String oper, String albumid, String[] id){
        Integer integer = specialService.selectById(albumid);
        if (oper.equals("add")){
            String s = UUID.randomUUID().toString();
            chapter.setId(s);
            chapter.setSpecial_id(albumid);
            chapterService.add(chapter);
            specialService.updateCount(integer+1,albumid);
            return s;
        }else if(oper.equals("del")){
            specialService.updateCount(integer-id.length,albumid);
            chapterService.del(id);
        }else {
            chapter.setSpecial_id(albumid);
            chapterService.update(chapter);
        }
        return null;
    }
    @RequestMapping("upload")
    public void upload(MultipartFile name, String id, HttpSession session) throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {
        String realPath = session.getServletContext().getRealPath("/audio");
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        String originalFilename = name.getOriginalFilename();
        String newFileName = new Date().getTime() + "_" + originalFilename;
        try {
            name.transferTo(new File(realPath,newFileName));

        } catch (Exception e) {
            e.printStackTrace();
        }
        String path = "/audio/"+newFileName;
        //获取文件位置
        String realPath1 = session.getServletContext().getRealPath(path);
        File file1 = new File(realPath1);
        //获取文件大小  单位是字节 byte
        long length = file1.length();
        //获取音频时长 单位是秒      AudioFile类需要引入额外依赖 jaudiotagger
        AudioFile read = AudioFileIO.read(file1);
        AudioHeader header = read.getAudioHeader();
        int trackLength = header.getTrackLength();
        //获取分钟数
        Integer m=trackLength/60;
        //获取秒秒数
        Integer s=trackLength%60;
        String date = m+"分"+s+"秒";
        //将文件大小强转成double类型
        double size=(double) length;
        //获取文件大小 单位是MB
        double ll=size/1024/1024;
        //取double小数点后两位  四舍五入
        BigDecimal bg = new BigDecimal(ll).setScale(2, RoundingMode.UP);
        String dd=bg+"MB";
        chapterService.updatePath(newFileName,dd,date,id);

    }
    @RequestMapping("/download")
    public void download(String filename, HttpServletRequest request, HttpServletResponse response)throws IOException {
        //获取目标文件夹的路径
        String realPath = request.getSession().getServletContext().getRealPath("/audio");
        //读入
        FileInputStream fis = new FileInputStream(new File(realPath,filename));

        //写出
        ServletOutputStream os= response.getOutputStream();
        //设置响应头
        response.setHeader("content-disposition","attachment;filename="+ URLEncoder.encode(filename,"utf-8"));

        IOUtils.copy(fis,os);
        //关流
        IOUtils.closeQuietly(fis);
        IOUtils.closeQuietly(os);

    }



}
