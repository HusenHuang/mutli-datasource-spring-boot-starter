package com.rootcloud.datasource.spring.boot.autoconfigure;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/***
 说明: 
 @author MrHuang
 @date 2020/9/8 20:26
 @desc
 ***/
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则，/**表示拦截所有请求
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new RouteDataSourceWebIntercetor())
                .addPathPatterns("/**")
                .excludePathPatterns("/actuator/**", "/error");
    }
}
