package com.snowlink.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.snowlink.bean.TsProjectDoc;
import com.snowlink.service.TsProjectDocService;
import com.snowlink.utils.FileDownload;
import com.snowlink.utils.FileUpload;

@Controller
@RequestMapping("/tsProjectDoc")
public class TsProjectDocController {
	@Autowired
	private TsProjectDocService service;
	
	@RequestMapping(value = "/blurryQuery", produces = "text/html;charset=UTF-8")
	public void blurryQuery(HttpServletRequest request, HttpServletResponse response) {
		try {
			String projectDocName = request.getParameter("projectDocName") == null ? "" : request.getParameter("projectDocName");
			String projectDoccreator = request.getParameter("projectDoccreator") == null ? "" : request.getParameter("projectDoccreator");
			String projectDocupdator = request.getParameter("projectDocupdator") == null ? "" : request.getParameter("projectDocupdator");
			int docTypeId = Integer.parseInt(request.getParameter("docTypeId"));
			int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			TsProjectDoc project = new TsProjectDoc();
			project.setDocName(projectDocName);
			project.setCreator(projectDoccreator);
			project.setUpdator(projectDocupdator);
			project.setDocTypeId(docTypeId);
			project.setPageNumber((pageNumber - 1) * pageSize);
			project.setPageSize(pageSize);
			JSONObject obj  = service.BlurryQuery(project);
			response.getWriter().print(JSONArray.toJSON(obj));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "/upload", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public  void upload(HttpServletRequest request,HttpServletResponse response,MultipartFile myFile) {
		try {
			FileUpload fu = new FileUpload();
			MultipartFile picFile = null;
			if(myFile != null){
				picFile = myFile;
			}
			
			response.getWriter().print(JSONArray.toJSON(fu.updateFile(request, response, picFile)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/insertUpload")
	@ResponseBody
	public void insertUpload(HttpServletRequest request,HttpServletResponse response){
		try{
			String url = request.getParameter("url");
			String docName = request.getParameter("docname");//�û�������
			String doc = request.getParameter("doc");//�ϴ��ļ���
			Integer docTypeId = Integer.parseInt(request.getParameter("docTypeId"));
			TsProjectDoc project = new TsProjectDoc();
			HttpSession session = request.getSession();
			String user = (String) session.getAttribute("user");
			Date time= new java.sql.Date(new java.util.Date().getTime());
			String houzhuiming = request.getParameter("houzhuiming");
			docName=docName+"."+houzhuiming;
			
			project.setDocUrl(url);
			project.setDocName(docName);
			project.setDocTypeId(docTypeId);
			project.setCreateTime(time);
			project.setUpdateTime(time);
			project.setCreator(user);
			project.setUpdator(user);
			Map<String,String> map = new HashMap<String, String>();
			if(service.insertUpload(project) > 0){
				map.put("result", "success");
			}else{
				map.put("result", "fail");
			}
			response.getWriter().print(map);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/download")
	@ResponseBody
	public void download(HttpServletRequest request,HttpServletResponse response){
		String url = request.getParameter("url");
		String docName = service.querydocName(url);
		FileDownload fd = new FileDownload();
		fd.download(request, response, "\\"+url.split("/")[url.split("/").length-1], docName);
	}
	
	
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(HttpServletRequest request,HttpServletResponse response){
		try {
			String idStr = request.getParameter("id");
			int id = Integer.parseInt(idStr);
			response.getWriter().print(JSONArray.toJSON(service.delete(id)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
