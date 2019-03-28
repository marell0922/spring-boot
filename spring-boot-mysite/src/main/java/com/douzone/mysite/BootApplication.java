package com.douzone.mysite;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.douzone.security.AuthInterceptor;
import com.douzone.security.AuthLoginInterceptor;
import com.douzone.security.AuthLogoutInterceptor;
import com.douzone.security.AuthUserHandlerMethodArgumentResolver;

/*@SpringBootConfiguration
@EnableAutoConfiguration	
@ComponentScan("com.douzone.hellospring.controller")*/

@SpringBootApplication
public class BootApplication {
	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

	
	@Bean
	public WebMvcConfigurer webMvcConfigurerAdapter() {
		return new WebMvcConfigurer() {
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(authLoginInterceptor()).addPathPatterns("/user/auth");
				registry.addInterceptor(authLogoutInterceptor()).addPathPatterns("/user/logout");
				registry.addInterceptor(authInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/auth")
				.excludePathPatterns("/user/logout").excludePathPatterns("/assets/*");
			}
			
			@Override
			public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
				// TODO Auto-generated method stub
				resolvers.add(authUserHandlerMethodArgumentResolver());
			}
			
			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {
				// TODO Auto-generated method stub
				registry.addResourceHandler("/uploads/images/**").addResourceLocations("file:D:/uploads/");
			}
			
		};
		
	}
	
	@Bean
	public AuthLoginInterceptor authLoginInterceptor() {
		return new AuthLoginInterceptor();
	}

	@Bean
	public AuthLogoutInterceptor authLogoutInterceptor() {
		return new AuthLogoutInterceptor();
	}

	@Bean
	public AuthInterceptor authInterceptor() {
		return new AuthInterceptor();
	}
	
	@Bean
	public AuthUserHandlerMethodArgumentResolver authUserHandlerMethodArgumentResolver() {
		return new AuthUserHandlerMethodArgumentResolver();
	}
	
	
}
