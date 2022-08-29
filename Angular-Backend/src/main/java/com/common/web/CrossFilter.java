package com.common.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CrossFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
	    HttpServletRequest request = HttpServletRequest.class.cast(servletRequest);
		HttpServletResponse response = (HttpServletResponse) servletResponse;
	    
	    response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");     //허용할 Origin(요청 url) : "*" 의 경우 모두 허용
	    response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST, GET, DELETE, PUT");     //허용할 request http METHOD : POST, GET, DELETE, PUT
//	    response.setHeader("Access-Control-Max-Age", "3600");     //브라우저 캐시 시간(단위: 초) : "3600" 이면 최소 1시간 안에는 서버로 재요청 되지 않음
	    response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "X-Requested-With, Content-Type");
	    
	    if(StringUtils.containsIgnoreCase(RequestMethod.OPTIONS.name(), request.getMethod())) {
	    	response.setStatus(HttpServletResponse.SC_OK);
	    	return;
	    }
	    
	    chain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
