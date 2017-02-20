package com.zrar.note.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccessFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		Cookie[] cookies = request.getCookies();
		String token = null;
		if(cookies != null){
			for(Cookie c: cookies){
                if("LoginAuthorization".equals(c.getName())){
                    token=c.getValue();
                    break;
                }
            }
		}
		if(token==null){
            //重定向到登陆页面
            response.sendRedirect("log_in.html");
            return;
        }
        //执行后续的请求, 也就是执行 edit.html
        chain.doFilter(req, res);

	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
