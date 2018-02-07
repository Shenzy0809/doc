package com.snowlink.utils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	//锟较达拷Excel锟侥硷拷
	 public Map<String, Object> updateFile(HttpServletRequest request,HttpServletResponse response,MultipartFile myFile){
        Map<String, Object> json = new HashMap<String, Object>();
        try {
            //锟斤拷锟斤拷募锟斤拷锟阶猴拷锟斤拷锟�
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            //图片锟斤拷锟斤拷
            String name = df.format(new Date());

            Random r = new Random();
            for(int i = 0 ;i<3 ;i++){
                name += r.nextInt(10);
            }
            //
            
            String originalFilename = myFile.getOriginalFilename();
            String ext = FilenameUtils.getExtension(myFile.getOriginalFilename()).toLowerCase();
            //锟斤拷锟斤拷图片       File位锟斤拷 锟斤拷全路锟斤拷锟斤拷   /upload/fileName.jpg
            String url = request.getSession().getServletContext().getRealPath("/upload");
            //锟斤拷锟铰凤拷锟�
            String path = "\\"+name + "." + ext;
            File file = new File(url);
            if(!file.exists()){
                file.mkdirs();
            }
            String k = originalFilename.split("."+ext)[0]+"_"+name+"."+ext;
            myFile.transferTo(new File(url+"\\"+k));
            json.put("url", "upload/"+originalFilename.split("."+ext)[0]+"_"+name+"."+ext);
            json.put("originalFilename", originalFilename);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json ;

    }
}
