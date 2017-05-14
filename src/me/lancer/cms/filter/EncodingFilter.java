package me.lancer.cms.filter;

import java.io.*;
import javax.servlet.*;


public class EncodingFilter implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			System.out.println("字符编码过滤器成功!");
		} catch (Exception e) {
			e.toString();
		}

		chain.doFilter(request, response);
	}

	public void destroy() {

	}
}
