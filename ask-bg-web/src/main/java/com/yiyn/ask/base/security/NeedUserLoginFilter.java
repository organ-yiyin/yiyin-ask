package com.yiyn.ask.base.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class NeedUserLoginFilter extends GenericFilterBean{
	
	public static final String loginPage = "/login.jsp";
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
		
        System.out.println("==========" + request.getServletPath());
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if(auth == null){
			response.sendRedirect(request.getContextPath() + loginPage);
		}
		else{
			chain.doFilter(request, response);
		}
		
	}
	
}
