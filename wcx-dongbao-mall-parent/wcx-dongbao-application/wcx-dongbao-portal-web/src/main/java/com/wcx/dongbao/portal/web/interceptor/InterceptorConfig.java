package com.wcx.dongbao.portal.web.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

//将我们写的拦截器配置进去
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Resource
    AuthInterceptor authInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user-member/login")
                .excludePathPatterns("/user-member/register")
                .excludePathPatterns("/code/**")
                .excludePathPatterns("/happy-code/**")
                .excludePathPatterns("/easy-code/**")
                .excludePathPatterns("/k-code/**")
        ;
    }

    @Bean
    public AuthInterceptor authInterceptor(){//注入拦截器
        return new AuthInterceptor();
    }
}
