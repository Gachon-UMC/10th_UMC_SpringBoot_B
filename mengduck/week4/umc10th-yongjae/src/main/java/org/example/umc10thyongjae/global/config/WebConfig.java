package org.example.umc10thyongjae.global.config;

import org.example.umc10thyongjae.global.apiPayload.handler.HeaderAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HeaderAuthInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/auth/signup"
                );
    }
}