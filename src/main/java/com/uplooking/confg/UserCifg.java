package com.uplooking.confg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


//@Configuration
public class UserCifg  extends WebMvcConfigurerAdapter{
	
	@Autowired
	@Qualifier("userInterceptor")
	private UserInterceptor userInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		 registry.addInterceptor(userInterceptor).addPathPatterns("/**").excludePathPatterns("/user/**").excludePathPatterns("/");
	}
}
