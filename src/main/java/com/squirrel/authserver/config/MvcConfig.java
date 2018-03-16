package com.squirrel.authserver.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.squirrel.authserver.authorization.interceptor.AuthorizationInterceptor;
import com.squirrel.authserver.authorization.resolvers.CurrentUserMethodArgumentResolver;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private AuthorizationInterceptor authorizationInterceptor;
	
	@Autowired
	private CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(authorizationInterceptor);
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		super.addArgumentResolvers(argumentResolvers);
		argumentResolvers.add(currentUserMethodArgumentResolver);
	}
}
