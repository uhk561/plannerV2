package com.plannerv2.config;

import com.plannerv2.common.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

        public FilterRegistrationBean<LoginFilter> loginFilter(LoginFilter loginFilter) {

            FilterRegistrationBean<LoginFilter> bean = new FilterRegistrationBean<>();

            bean.setFilter(loginFilter);
            bean.addUrlPatterns("/*"); // 모든 요청 필터링
            bean.setOrder(1);          // 필터 우선순위

            return bean;
        }
    }
