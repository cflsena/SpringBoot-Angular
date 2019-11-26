package com.example.dev.backend.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.dev.backend.api.interceptor.GenericControllerInterceptor;

@Configuration
public class GenericControllerInterceptorConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new GenericControllerInterceptor());
	}
}
