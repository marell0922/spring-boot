package com.douzone.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter {
	
 // 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		// 여기서 new UserService를 해버리면 원래의 service가 아니라, 새로운 service를 생성해서 사용하는 거임.
		ApplicationContext ac=WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		UserService userService=ac.getBean(UserService.class);
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		UserVo userVo=userService.getUser(email, password);
		
		if(userVo ==null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		HttpSession session=request.getSession();
		session.setAttribute("authuser", userVo);
		response.sendRedirect(request.getContextPath()+"/");
		return false;
	}
	
}
