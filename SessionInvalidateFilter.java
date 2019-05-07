package com.jbhunt.infrastructure.platform.configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class SessionInvalidateFilter implements Filter {

	public SessionInvalidateFilter() {
	  //Do nothing for initialization
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	  //Do nothing for init
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		httpServletRequest.getSession().invalidate();
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {
    //Do nothing for destroy
	}

}
