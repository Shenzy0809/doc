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
import com.snowlink.bean.TsProject;
import com.snowlink.bean.TsProjectDoc;
import com.snowlink.bean.TsProjectDocType;
import com.snowlink.service.TsProjectService;
import com.snowlink.service.TsUserService;

@Controller
@RequestMapping("/tsProject")
public class TsProjectController {

	@Autowired
	private TsProjectService service;
	
	@RequestMapping(value = "/index", produces = "text/html;charset=UTF-8")
	public void index(HttpServletRequest request,HttpServletResponse response){
		try {
			String projectName = request.getParameter("projectName") == null ? "" : request.getParameter("projectName");
			String creator = request.getParameter("creator") == null ? "" : request.getParameter("creator");
			String updator = request.getParameter("updator") == null ? "" : request.getParameter("updator");
			int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			
			TsProject tsProject = new TsProject();
			tsProject.setCreator(creator);
			tsProject.setProjectName(projectName);
			tsProject.setUpdator(updator);
			tsProject.setPageNumber((pageNumber - 1) * pageSize);
			tsProject.setPageSize(pageSize);
			JSONObject obj = service.queryAll(tsProject);
			response.getWriter().print(JSONArray.toJSON(obj));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/inserProject", produces = "text/html;charset=UTF-8")
	public void inserProject(HttpServletRequest request,HttpServletResponse response) {
		try {
			String projectName  = request.getParameter("projectName");
			Date time= new java.sql.Date(new java.util.Date().getTime());
			
			TsProject project = new TsProject();
			project.setProjectName(projectName);
			project.setCreateTime(time);
			//暂时没有创建人   
			//project.setCreator(creator);
			HttpSession session = request.getSession();
			String user =(String) session.getAttribute("user");
			project.setCreator(user);
			project.setUpdateTime(time);
			project.setUpdator(user);
			int result = service.InsertProject(project);
			response.getWriter().print(JSONArray.toJSON(result));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/delProject", produces = "text/html;charset=UTF-8")
	public void delProject(HttpServletRequest request,HttpServletResponse response) {
		try {
			String project_id  = request.getParameter("project_id");
			
			int result = service.delProject(project_id);
			response.getWriter().print(JSONArray.toJSON(result));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getProjectName", produces = "text/html;charset=UTF-8")
	public void getProjectName(HttpServletRequest request,HttpServletResponse response) {
		try {
			String projectId  = request.getParameter("projectId");
			
			String projectName = service.getProjectName(projectId);
			response.getWriter().print(JSONArray.toJSON(projectName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "/ChangeProject", produces = "text/html;charset=UTF-8")
	public void ChangeProject(HttpServletRequest request,HttpServletResponse response) {
		try {
			String projectName  = request.getParameter("projectName");
			int projectId = Integer.parseInt(request.getParameter("projectId"));
			Date time= new java.sql.Date(new java.util.Date().getTime());
			HttpSession session = request.getSession();
			String user =(String) session.getAttribute("user");
			
			TsProject project = new TsProject();
			project.setUpdator(user);
			project.setUpdateTime(time);
			project.setProjectId(projectId);
			project.setProjectName(projectName);
			int result = service.ChangeProject(project);
			
			response.getWriter().print(JSONArray.toJSON(result));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value = "/SubordinateDoc", produces = "text/html;charset=UTF-8")
	public void SubordinateDoc(HttpServletRequest request,HttpServletResponse response) {
		try {
			int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			int projectId = Integer.parseInt(request.getParameter("projectId"));
			TsProjectDocType tsProject = new TsProjectDocType();
			tsProject.setProjectId(projectId);
			tsProject.setPageNumber((pageNumber - 1) * pageSize);
			tsProject.setPageSize(pageSize);
			JSONObject obj=	service.SelectSubordinateDoc(tsProject);
			response.getWriter().print(JSONArray.toJSON(obj));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/queryProjectAllCountDoc", produces = "text/html;charset=UTF-8")
	public void queryProjectAllCountDoc(HttpServletRequest request,HttpServletResponse response) {
		try {
			int projectId = Integer.parseInt(request.getParameter("project_id"));
			int result = service.queryProjectAllCountDoc(projectId);
			response.getWriter().print(JSONArray.toJSON(result));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
