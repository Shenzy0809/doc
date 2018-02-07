package com.snowlink.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.snowlink.bean.TsBasicDocContent;
import com.snowlink.service.TsBasicDocContentService;
import com.snowlink.service.TsDocOptionService;


@Controller
@RequestMapping("/DocContent")
public class TsBasicDocContentController {
	
	@Autowired
	private TsBasicDocContentService Bservice;
	
	/**
	 * Ìø×ªµ½Ê×Ò³
	 * @return
	 */
	
	@RequestMapping("/index")
	public String toindex() {
		return "index";
	}
	
	@RequestMapping(value = "/changeManager", produces = "text/html;charset=UTF-8")
	public void changeManager(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			TsBasicDocContent content = new TsBasicDocContent();
			content.setId(id);
			TsBasicDocContent tscontent = Bservice.SelectAllByidno(content);
			response.getWriter().print(JSONArray.toJSON(tscontent));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/alertContent", produces = "text/html;charset=UTF-8")
	public void alertContent(HttpServletRequest request, HttpServletResponse response) {
		try {
			int title_type = Integer.parseInt(request.getParameter("title_type"));
			int ordercode = Integer.parseInt(request.getParameter("ordercode"));
			int id = Integer.parseInt(request.getParameter("id"));
			String title  = request.getParameter("title");
			String title_no  = request.getParameter("title_no");
			String content  = request.getParameter("content");
			TsBasicDocContent tbContent = new TsBasicDocContent();
			tbContent.setContent(content);
			tbContent.setOrdercode(ordercode);
			tbContent.setTitle_no(title_no);
			tbContent.setTitle_type(title_type);
			tbContent.setTitle(title);
			tbContent.setId(id);
			
			int result = Bservice.alertContent(tbContent);
			response.getWriter().print(JSONArray.toJSON(result));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "/delDocContent", produces = "text/html;charset=UTF-8")
	public void delDocContent(HttpServletRequest request, HttpServletResponse response) {
	   try {
		   int id = Integer.parseInt(request.getParameter("id"));
		   int result = Bservice.delDocContent(id);
		   response.getWriter().print(JSONArray.toJSON(result));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/AddContent", produces = "text/html;charset=UTF-8")
	public void AddContent(HttpServletRequest request, HttpServletResponse response) {
		try {
			int title_type=2;
			if(request.getParameter("title_type")==null||request.getParameter("title_type").equals("")) {
				 title_type=2;
			}else {
				 title_type = Integer.parseInt(request.getParameter("title_type"));
			}
			int ordercode = Integer.parseInt(request.getParameter("ordercode"));
			String title_no  = request.getParameter("title_no");
			String doc_id  = request.getParameter("doc_id");
			String doc_content  = request.getParameter("content");
			String title  = request.getParameter("title");
			
			TsBasicDocContent content = new TsBasicDocContent();
			content.setDoc_id(doc_id);
			content.setTitle_type(title_type);
			content.setOrdercode(ordercode);
			content.setTitle_no(title_no);
			content.setTitle(title);
			content.setContent(doc_content);
			
			int result = Bservice.AddNewContent(content);
			response.getWriter().print(JSONArray.toJSON(result));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/PreviewDoc", produces = "text/html;charset=UTF-8")
	public void PreviewDoc(HttpServletRequest request, HttpServletResponse response) {
		try {
			String doc_id  = request.getParameter("doc_id");
			List<TsBasicDocContent> tbList =Bservice.SelectAllByDocId(doc_id);
			response.getWriter().print(JSONArray.toJSON(tbList));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value = "/PreviewDoc1", produces = "text/html;charset=UTF-8")
	public void PreviewDoc1(HttpServletRequest request, HttpServletResponse response) {
		try {
			String doc_id  = request.getParameter("doc_id");
			List<TsBasicDocContent> tbList =Bservice.SelectAllByDocId(doc_id);
			response.getWriter().print(JSONArray.toJSON(tbList));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/CheckBasicCount", produces = "text/html;charset=UTF-8")
	public void CheckBasicCount(HttpServletRequest request, HttpServletResponse response) {
		try {
			int doc_id = Integer.parseInt(request.getParameter("doc_id"));
			int count = Bservice.CheckBasicCount(doc_id);
			HttpSession session = request.getSession();
			int level = (Integer)session.getAttribute("level");
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("count", count);
			map.put("level", level);
			response.getWriter().print(JSONArray.toJSON(map));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/selectOrder", produces = "text/html;charset=UTF-8")
	public void selectOrder(HttpServletRequest request, HttpServletResponse response) {
		try {
			int doc_id = Integer.parseInt(request.getParameter("doc_id"));
			int result = Bservice.selectOrder(doc_id);
			response.getWriter().print(JSONArray.toJSON(result));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
}
