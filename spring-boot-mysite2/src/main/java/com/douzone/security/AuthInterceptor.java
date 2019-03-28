package com.douzone.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.mysite.vo.UserVo;
import com.douzone.security.Auth.Role;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		//1.Handler 종류 확인
		// handler method  vs default servlet handler
		if(handler instanceof HandlerMethod==false) {
			return true;
		}
		
		//2.casting
		HandlerMethod handlerMethod=(HandlerMethod) handler;
		
		//3. method에서 @Auth 방아오기
		Auth auth=handlerMethod.getMethodAnnotation(Auth.class);
		
		//3-1. Method에 @Auth가 없으면, class type에도 있는 지 확인 해 보기
		if(auth==null) {
			auth=handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}
		
		
		//4.Method에 @Auth가 없으면, 매핑이 필요 x
		if(auth ==null) {
			return true;
		}
		
		//5.@ Auth 붙어있기 때문에, 로그인 여부(인증 여부)를 확인해야 한다.
		HttpSession session=request.getSession();
		UserVo authuser=null;
		if(session!=null) {
			authuser=(UserVo)session.getAttribute("authuser");
		}
		
		if(authuser==null) {
			response.sendRedirect(request.getContextPath()+"/user/login");
		}
		
		//5-1. role 비교 작업을 같이 해주어야 한다.
		Role role=auth.value();
		//session.auth.role equals로 비교작업
		
		//ADMIN ROLE 접근
		if("ADMIN".equals(authuser.getRole())==false){
			response.sendRedirect(request.getContextPath()+"/");
			return false;
		}
		
		//6. 접근 허용
		return true;
	}
	
}
