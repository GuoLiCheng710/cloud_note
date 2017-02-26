package com.zrar.note.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AccessFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		HttpSession session = request.getSession();
		String str = (String)session.getAttribute("LoginAuthorization");
		System.out.println("session:"+str);
		if(!"LonginSuccessful".equals(str)){
			response.sendRedirect("log_in.html");
            return;
		}
//		Cookie[] cookies = request.getCookies();
//		String token = null;
//		if(cookies != null){
//			for(Cookie c: cookies){
//                if("LoginAuthorization".equals(c.getName())){
//                    token=c.getValue();
//                    break;
//                }
//            }
//		}
//		if(token==null){
//            //为成功登陆时，跳转到登录页
//            response.sendRedirect("log_in.html");
//            return;
//        }
        //继续执行，跳转到edit.html
        chain.doFilter(req, res);

	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
