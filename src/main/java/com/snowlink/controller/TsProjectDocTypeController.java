package com.snowlink.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.snowlink.bean.TsProjectDocType;
import com.snowlink.service.TsProjectDocTypeService;

@Controller
@RequestMapping("/tsProjectDocType")
public class TsProjectDocTypeController {

	@Autowired
	private TsProjectDocTypeService service;
	
	@RequestMapping(value = "/index", produces = "text/html;charset=UTF-8")
	public void index(HttpServletRequest request, HttpServletResponse response) {
		try {
			String projectDocTypeName = request.getParameter("projectDocTypeName") == null ? "" : request.getParameter("projectDocTypeName");
			String projectDocTypecreator = request.getParameter("projectDocTypecreator") == null ? "" : request.getParameter("projectDocTypecreator");
			String projectDocTypeupdator = request.getParameter("projectDocTypeupdator") == null ? "" : request.getParameter("projectDocTypeupdator");
			int projectId = Integer.parseInt(request.getParameter("projectId"));
			int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			
			
			
			TsProjectDocType project = new TsProjectDocType();
			project.setProjectId(projectId);
			project.setCreator(projectDocTypecreator);
			project.setUpdator(projectDocTypeupdator);
			project.setDocTypeName(projectDocTypeName);
			project.setPageNumber((pageNumber - 1) * pageSize);
			project.setPageSize(pageSize);
			JSONObject obj = service.QueryAllBycondition(project);
			response.getWriter().print(JSONArray.toJSON(obj));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/inserProjectDocName", produces = "text/html;charset=UTF-8")
	public void inserProjectDocName(HttpServletRequest request, HttpServletResponse response) {
		try {
			String ProjectDocTypeName  = request.getParameter("ProjectDocTypeName");
			int projectId = Integer.parseInt(request.getParameter("projectId"));
			Date time= new java.sql.Date(new java.util.Date().getTime());//创建时间
			//创建人
			HttpSession session = request.getSession();
			String user = (String)session.getAttribute("user");
			
			TsProjectDocType project = new TsProjectDocType();
			project.setCreateTime(time);
			project.setProjectId(projectId);
			project.setCreator(user);
			project.setDocTypeName(ProjectDocTypeName);
			project.setUpdator(user);
			project.setUpdateTime(time);
			int result = service.InsertProjectDocTypeName(project);
			response.getWriter().print(JSONArray.toJSON(result));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/delProjectDocType", produces = "text/html;charset=UTF-8")
	public void delProjectDocType(HttpServletRequest request, HttpServletResponse response) {
		try {
			int docTypeId = Integer.parseInt(request.getParameter("docTypeId"));
			
			int result = service.delProjectDocTypeName(docTypeId);
			response.getWriter().print(JSONArray.toJSON(result));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getProjectDocTypeName", produces = "text/html;charset=UTF-8")
	public void getProjectDocTypeName(HttpServletRequest request, HttpServletResponse response) {
		try {
			int docTypeId = Integer.parseInt(request.getParameter("docTypeId"));
			String name = service.SelectNameByid(docTypeId);
			response.getWriter().print(JSONArray.toJSON(name));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/ChangeProjectDocType", produces = "text/html;charset=UTF-8")
	public void ChangeProjectDocType(HttpServletRequest request, HttpServletResponse response) {
		try {
			int docTypeId = Integer.parseInt(request.getParameter("docTypeid"));
			String DocTypeName  = request.getParameter("DocTypeName");
			HttpSession session = request.getSession();
			String user = (String)session.getAttribute("user");
			Date time= new java.sql.Date(new java.util.Date().getTime());//创建时间
			
			
			TsProjectDocType project = new TsProjectDocType();
			project.setDocTypeName(DocTypeName);
			project.setDocTypeId(docTypeId);
			project.setUpdateTime(time);
			project.setUpdator(user);
			int result = service.ChangeProjectDocTypeName(project);
			response.getWriter().print(JSONArray.toJSON(result));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/queryDocCountById", produces = "text/html;charset=UTF-8")
	public void queryDocCountById(HttpServletRequest request, HttpServletResponse response) {
		try {
			int docTypeId = Integer.parseInt(request.getParameter("docTypeId"));
			int count = service.queryDocCountById(docTypeId);
			response.getWriter().print(JSONArray.toJSON(count));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
