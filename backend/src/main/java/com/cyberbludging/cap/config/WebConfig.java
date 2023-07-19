package com.cyberbludging.cap.config;

import com.cyberbludging.cap.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new AuthenticationInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/auth/**", "/swagger-ui.html", "/swagger-resources/**", "/webjars/**");
    }
}
