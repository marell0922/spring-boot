package com.douzone.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.douzone.mysite.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver{
	
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("resolveArgument");
		if(supportsParameter(parameter)==false) {
			return WebArgumentResolver.UNRESOLVED;
		}
		
		//@AuthUser가 붙어있고, type=UserVo
		// 해당되는 was의 http request를 얻어옴.
		HttpServletRequest request=(HttpServletRequest)webRequest.getNativeRequest();
		
		HttpSession session=request.getSession();
		if(session==null) {
			return WebArgumentResolver.UNRESOLVED; // == return null;
		}
		
		return session.getAttribute("authuser");
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// TODO Auto-generated method stub
		AuthUser authUser=parameter.getParameterAnnotation(AuthUser.class);
		//@AuthUser가 안 붙어있음
		if(authUser==null) {
			return false;
		}
		
		//parameter type - userVo인지 확인 작업
		if(parameter.getParameterType().equals(UserVo.class)==false) {
			return false;
		}
		return true;
	}
	
}
	