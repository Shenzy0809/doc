package com.snowlink.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.snowlink.bean.TsSysUser;
import com.snowlink.service.TsUserService;

@Controller
@RequestMapping("/user")
public class TsUserController {
	
	@Autowired
	private TsUserService service;

	
	@RequestMapping(value = "/login", produces = "text/html;charset=UTF-8")
	public void login(HttpServletRequest request, HttpServletResponse response) {
		try {
			String name  = request.getParameter("name");
			String password  = request.getParameter("password");
			Map<String, Integer> map = new HashMap<String, Integer>();
			int result=0;
			if (name == null || name.equals("") || password == null || password.equals("")) {
				result=0;//¥ÌŒÛ
				map.put("result", 0);
			}else {
				String pwd = service.CheckLogin(name);
				if(pwd==""||pwd==null) {
					map.put("result", 3);//’À∫≈≤ª¥Ê‘⁄
				}else if(password.equals(pwd)||password==pwd) {
					result =1;//—È÷§≥…π¶
					HttpSession session = request.getSession();
					//¥Ê∑≈Session
					TsSysUser user = service.QueryIdName(name);
					session.setAttribute("id", user.getAdminId());
					session.setAttribute("user", user.getName());
					session.setAttribute("level", user.getAuthorityLevel());
					map.put("result", 1);
					map.put("level", user.getAuthorityLevel());
				}else {
					result=2;//’À∫≈√‹¬Î¥ÌŒÛ
					map.put("result", 2);
				}
			}//else
			response.getWriter().print(JSONArray.toJSON(map));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getsession", produces = "text/html;charset=UTF-8")
	public void getsession(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			int level = (Integer)session.getAttribute("level");
			response.getWriter().print(JSONArray.toJSON(level));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/loginout", produces = "text/html;charset=UTF-8")
	public void loginout(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			session.invalidate();
			int result =1 ;
			response.getWriter().print(JSONArray.toJSON(result));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
