package com.jbhunt.infrastructure.platform.configuration;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.jbhunt.biz.security.sso.filters.AuthenticationFilter;
import com.jbhunt.biz.security.sso.filters.LogoutFilter;
import com.jbhunt.biz.security.ui.filter.AuthorizationEnforcementFilter;
import com.jbhunt.lib.lnf.session.SessionTimeoutFilter;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * This class initializes the application and add the servlets, filters and
 * listeners to the context.
 *
 */
@Slf4j
public class ApplicationInitializer implements WebApplicationInitializer {
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet
	 * .ServletContext)
	 */
	@Override
	public void onStartup(ServletContext context) throws ServletException {

		log.info("WebApplicationInitializer..onStartup");
		// Character encoding filter
		FilterRegistration characterEncodingFilter = context.addFilter("encodingFilter", CharacterEncodingFilter.class);
		characterEncodingFilter.setInitParameter("encoding", "UTF-8");
		characterEncodingFilter.setInitParameter("forceEncoding", "true");
		characterEncodingFilter.addMappingForUrlPatterns(null, false, "/*");

		// Session Invalidate Filter
		context.addFilter("SessionInvalidateFilter", SessionInvalidateFilter.class).addMappingForUrlPatterns(null,
				false, "/logout/");

		// Authentication filter
		FilterRegistration authenticationFilter = context.addFilter("jbhSSOFilter", AuthenticationFilter.class);
		authenticationFilter.setInitParameter("app.filter.wrap.request", "true");
		authenticationFilter.addMappingForServletNames(null, true, "app");
		authenticationFilter.addMappingForUrlPatterns(null, false, "/*");
		authenticationFilter.addMappingForUrlPatterns(null, false, "*.html");

		// Authorization filter
		FilterRegistration authorizationFilter = context.addFilter("AuthFilter", AuthorizationEnforcementFilter.class);
		authorizationFilter.setInitParameter("loginUrl", "/signin.html");
		authorizationFilter.setInitParameter("stopFilterProcess", "true");
		authorizationFilter.setInitParameter("notAuthorizedUrl", "/securepages/notauthorized");
		authorizationFilter.setInitParameter("secureUrls",
				"/user /messaging/* /static/*");
		authorizationFilter.setInitParameter("useridResolverType", "Container");
		authorizationFilter.addMappingForUrlPatterns(null, true, "/user");

		context.addFilter("SessionTimeoutFilter", SessionTimeoutFilter.class).addMappingForUrlPatterns(null, true, "*");
		// Logout filter
    FilterRegistration logoutFilter = context.addFilter("LogoutFilter", LogoutFilter.class);
    String casLogoutUrlPrefix = System.getProperty("cas.server.logout.url");
    String casPlatformLogoutUrl = "";
    if(casLogoutUrlPrefix!=null){
      if(casLogoutUrlPrefix.contains("dev")){
        casPlatformLogoutUrl = casLogoutUrlPrefix+"?service=http://scm-dev.jbhunt.com/platform";
      }else if(casLogoutUrlPrefix.contains("tst")){
        casPlatformLogoutUrl = casLogoutUrlPrefix+"?service=http://scm-tst.jbhunt.com/platform";
      }else{
        casPlatformLogoutUrl = casLogoutUrlPrefix+"?service=http://scm.jbhunt.com/platform";
      }
      logoutFilter.setInitParameter("casServerLogoutUrl", casPlatformLogoutUrl);
    }
    logoutFilter.addMappingForUrlPatterns(null, true, "/logout/");

	}
}
