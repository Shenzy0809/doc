package com.snowlink.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;



public class LoginInterceptor implements HandlerInterceptor {
	private static final String LOGIN_URL = "/user/login"; 
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
//		String requestURI = request.getRequestURI();  
//        if(requestURI.indexOf("editClientIfo.action")>0){  
//            //说明处在编辑的页面  
//            HttpSession session = request.getSession();  
//            String username = (String) session.getAttribute("user");  
//            if(username!=null){  
//                //登陆成功的用户  
//                return true;  
//            }else{  
//               //没有登陆，转向登陆界面  
//                request.getRequestDispatcher("/login.jsp").forward(request,arg1);  
//              return false;  
//            }  
//        }else{  
//            return true;  
//        } 
//		 if ("GET".equalsIgnoreCase(request.getMethod())) {
//             RequestUtil.saveRequest();
//	     }
	     String requestUri = request.getRequestURI();
	     String contextPath = request.getContextPath();
	     String url = requestUri.substring(contextPath.length());  
//	     String px = (url.split(".").length > 1 ? url.split(".")[url.split(".").length-1] :"");
//	     System.out.println("px:"+px); 
	     if ("/user/login".equals(url) || url.indexOf("js") > 0 || url.indexOf("css") > 0) {                  
	           
	    	 return true;
	     }else {               
	             String username =(String)request.getSession().getAttribute("user");
	             if(username == null){
//	            	 Cookie[] cookies = request.getCookies();
//	            	 if(cookies != null || cookies.length > 0){
//	            		 for(Cookie cookie : cookies){
//	            			 if(){
//	            				 
//	            			 }
//	            		 }
//	            	 }
	            	 response.sendRedirect("http://" + request.getRequestURL().toString().split("/")[2]+"/snowlink_doc/login.html"); 
	                // request.getRequestDispatcher("/login.html").forward(request, response);
	                return false;
	             }else{
	               return true;  
	             } 
	    }
	}

}
