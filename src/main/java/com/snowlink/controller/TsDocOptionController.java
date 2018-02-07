package com.snowlink.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.snowlink.bean.TsBasicDocContent;
import com.snowlink.bean.TsDocOption;
import com.snowlink.service.TsBasicDocContentService;
import com.snowlink.service.TsDocOptionService;

@Controller
@RequestMapping("/DocOption")
public class TsDocOptionController {
	
	@Autowired
	private TsDocOptionService Dservice;
	
	@Autowired
	private TsBasicDocContentService Bservice;
	
	@RequestMapping("/DocNQuery")
	public void DocNQuery(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<TsDocOption> DoList = Dservice.QueryDocNameAll();	
			response.getWriter().print(JSONArray.toJSON(DoList));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "/insertDoc", produces = "text/html;charset=UTF-8")
	public void insertDoc(HttpServletRequest request, HttpServletResponse response) {
		
			try {
				String doc_name  = request.getParameter("Doc_name");
				TsDocOption option = new TsDocOption();
				Date time= new java.sql.Date(new java.util.Date().getTime());
				option.setDoc_name(doc_name);
				option.setCreate_time(time);
				option.setCreator(null);
				int result  = Dservice.InsertDocName(option);
				response.getWriter().print(JSONArray.toJSON(result));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@RequestMapping(value = "/DocContent", produces = "text/html;charset=UTF-8")
	public void QueryDocContent(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			TsBasicDocContent content = new TsBasicDocContent();
			
			String Doc_id  = request.getParameter("doc_id");
			int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			int pageSize = Integer.parseInt(request.getParameter("pageSize"));
			content.setPageNumber((pageNumber - 1) * pageSize);
			content.setPageSize(pageSize);
			content.setDoc_id(Doc_id);
			
			JSONObject obj  = Bservice.QueryDocContent(content);
			response.getWriter().print(JSONArray.toJSON(obj));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/AlertDoc", produces = "text/html;charset=UTF-8")
	public void AlertDoc(HttpServletRequest request, HttpServletResponse response) {

		try {
			List<TsDocOption> tdList = Dservice.QueryDocNameAll();
			int count = Dservice.DocCount();
			JSONObject obj = new JSONObject();
			obj.put("DNameList", tdList);
			obj.put("count", count);
			response.getWriter().print(JSONArray.toJSON(obj));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(value = "/amendDocName", produces = "text/html;charset=UTF-8")
	public void amendDocName(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String doc_name  = request.getParameter("doc_name");
			TsDocOption option = new TsDocOption();
			Date time= new java.sql.Date(new java.util.Date().getTime());
			option.setUpdate_time(time);
			option.setUpdator(null);
			option.setId(id);
			option.setDoc_name(doc_name);
			
			int result =Dservice.amendDocNameById(option);
			response.getWriter().print(JSONArray.toJSON(result));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/delDoc", produces = "text/html;charset=UTF-8")
	public void delDoc(HttpServletRequest request, HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			int result = Dservice.delDoc(id);
			response.getWriter().print(JSONArray.toJSON(result));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
}
