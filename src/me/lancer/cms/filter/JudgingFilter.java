package me.lancer.cms.filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JudgingFilter implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest httprequest = (HttpServletRequest) request;
			HttpServletResponse httpresponse = (HttpServletResponse) response;
			String strError = null;
//			System.out.println("session"+(httprequest.getSession()==null?"true":"false"));
			if(httprequest.getSession().getAttribute("error")!=null){
				if(httprequest.getSession().getAttribute("access")==null){
//					System.out.println("登录失败:" + httprequest.getSession().getAttribute("error").toString());
					httpresponse.sendRedirect(httprequest.getContextPath()+"/index.jsp");
//					httpresponse.setHeader("refresh", "1;URL=index.jsp");
				}
			}else if(httprequest.getSession().getAttribute("error")==null){
				if(httprequest.getSession().getAttribute("access")==null){
//					System.out.println("尚未登录");
					if(!httprequest.getServletPath().equals("/index.jsp")){
						httpresponse.sendRedirect(httprequest.getContextPath()+"/index.jsp");
//						httpresponse.setHeader("refresh", "URL=index.jsp");
					}
				}else{
					System.out.println("登录成功:权限" + httprequest.getSession().getAttribute("access").toString());
					if(httprequest.getSession().getAttribute("password").toString().equals("123456")){
						httpresponse.sendRedirect(httprequest.getContextPath()+"/passw.jsp");
					}
				}
			}
//			System.out.println("error:"+ (httprequest.getSession().getAttribute("error")==null?"true":"false"));
//			System.out.println("access:"+ (httprequest.getSession().getAttribute("access")==null?"true":"false"));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("登录异常:" + e.toString());
		}
		chain.doFilter(request, response);
	}

	public void destroy() {

	}
}
