/**
 * 
 */
package com.douzone.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jiyeon
 *
 */
@Target({ElementType.METHOD, ElementType.TYPE}) // method에만 붙일 수 있는 타입으로 지정.
@Retention(RetentionPolicy.RUNTIME) // 실행되는 시점.
public @interface Auth {
	public enum Role{ADMIN, USER}
	
	Role  /*type*/ value() default Role.USER;
	//String value() default "USER";
	
	// Test
	//int method() default 1;
	
}
