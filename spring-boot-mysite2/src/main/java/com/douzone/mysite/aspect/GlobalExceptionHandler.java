package com.douzone.mysite.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
// @Component global이라서 충돌남

public class GlobalExceptionHandler {

	@AfterThrowing(value="execution(* *..*.*.*(..))", throwing="ex")
	public void afterThrowingAdvice(Throwable ex) {
		System.out.println("call[afterThrowing advice] : "+ ex);
	}
}
