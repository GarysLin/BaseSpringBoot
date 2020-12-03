package com.gary.cloudinteractive.webapi.config;

import com.gary.cloudinteractive.webapi.filter.OnlyAfterRequestLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogConfiguration {
  @Bean
  public OnlyAfterRequestLoggingFilter requestLoggingFilter() {
    OnlyAfterRequestLoggingFilter loggingFilter = new OnlyAfterRequestLoggingFilter();
    loggingFilter.setIncludeClientInfo(false);
    loggingFilter.setIncludeQueryString(true);
    loggingFilter.setIncludePayload(true);
    loggingFilter.setIncludeHeaders(false);
    loggingFilter.setMaxPayloadLength(9999);//會去cache一個空間 要注意大小
    return loggingFilter;
  }

  @Bean
  public FilterRegistrationBean<OnlyAfterRequestLoggingFilter> loggingFilterRegistration() {
    FilterRegistrationBean<OnlyAfterRequestLoggingFilter> registration = new FilterRegistrationBean<>(requestLoggingFilter());
    registration.addUrlPatterns("/api/*"); //API pattern
    return registration;
  }
}
