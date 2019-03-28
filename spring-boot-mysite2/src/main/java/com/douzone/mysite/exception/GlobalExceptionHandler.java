package com.douzone.mysite.exception;


import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.douzone.mysite.dto.JSONResult;
import com.fasterxml.jackson.databind.ObjectMapper;


@ControllerAdvice //aop controller error 집합소
public class GlobalExceptionHandler {
	
	private static final Log LOG=LogFactory.getLog(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	public void handlerException(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
		//1.logging
		//e.printStackTrace();
		StringWriter errors=new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		//System.out.println(errors.toString());
		LOG.error(errors.toString());
		//2.시스템 오류 안내 화면
		/*ModelAndView mav=new ModelAndView();
		mav.setViewName("error/exception");
		mav.addObject("errors", errors.toString());
		return mav;*/// view , model error 인지 구분해야 함.
		String accept=request.getHeader("accept"); 
		if(accept.equals(".*application/json.*")) { // 앞 뒤에 어떤 문자가 있든 - 정규표현식
			//json 응답
			response.setStatus(HttpServletResponse.SC_OK); //서버의 ERROR 일뿐, 통신상 문제가 아님.
			OutputStream out=response.getOutputStream();
			JSONResult jsonResult=JSONResult.fail(errors.toString());
			//JACKSON LABRARY USE
			out.write(new ObjectMapper().writeValueAsString(jsonResult).getBytes("utf-8"));
			out.flush();
			out.close();
			
		}else {
			//html 응답
			request.setAttribute("uri", request.getRequestURI());
			request.setAttribute("exception", errors.toString());
			request.
			getRequestDispatcher("/WEB-INF/views/error/exception.jsp")
			.forward(request, response);
		}
	}
}
