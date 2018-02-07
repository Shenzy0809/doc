package com.snowlink.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class FileDownload {

	public void download(HttpServletRequest request,HttpServletResponse response,String docname,String realName){
		try {
			//模拟文件，myfile.txt为需要下载的文件  
	        String fileName = request.getSession().getServletContext().getRealPath("upload")+docname;  
	        //获取输入流  
	        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));  
	        //假如以中文名下载的话  
	        String filename = realName;  
	        //转码，免得文件名中文乱码  
	        filename = URLEncoder.encode(filename,"UTF-8");  
	        //设置文件下载头  
	        response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
	        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
	        response.setContentType("multipart/form-data");   
	        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
	        int len = 0;  
	        while((len = bis.read()) != -1){  
	            out.write(len);  
	            out.flush();  
	        }  
	        out.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
