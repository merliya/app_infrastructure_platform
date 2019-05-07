package com.jbhunt.infrastructure.platform.configuration;

import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.github.ziplet.filter.compression.CompressingFilter;

@Configuration
public class AppConfiguration {

	@Bean
	public FilterRegistrationBean filterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(compressionFilter());
		registration.setName("compressionFilter");
		registration.setOrder(1);
		return registration;
	}

	/**
	 * the compressing filter will gzip web service responses
	 *
	 * @return CompressingFilter
	 */
	@Bean
	public Filter compressionFilter() {
		return new CompressingFilter();
	}

	@Bean
	public WebMvcConfigurerAdapter webConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {

                registry.addResourceHandler("/**").addResourceLocations("classpath:/public/")
          .setCacheControl(CacheControl.maxAge(1,TimeUnit.MINUTES).mustRevalidate());

				registry.setOrder(2).addResourceHandler("/index.html").addResourceLocations("classpath:/public/")
          .setCacheControl(CacheControl.noCache());
			}
			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
				registry.setOrder(1);
			    registry.addViewController("/").setViewName("forward:/index.html");
			}
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("https://*.jbhunt.com");
      }
		};
	}
  @Bean
  public Filter shallowEtagHeaderFilter() {
    return new ShallowEtagHeaderFilter();
  }

  @Bean
  public FilterRegistrationBean shallowEtagHeaderFilterRegistration() {
    FilterRegistrationBean result = new FilterRegistrationBean();
    result.setFilter(this.shallowEtagHeaderFilter());
    result.addUrlPatterns("/header/*");
    result.setName("shallowEtagHeaderFilter");
    result.setOrder(1);
    return result;
  }
}
