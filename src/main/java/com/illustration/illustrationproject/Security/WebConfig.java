package com.illustration.illustrationproject.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final ImageCorsInterceptor imageCorsInterceptor;

    public WebConfig(ImageCorsInterceptor imageCorsInterceptor) {
        this.imageCorsInterceptor = imageCorsInterceptor;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/content/**")
                .addResourceLocations("classpath:static/uploads/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Apply the interceptor to URL patterns for images
        registry.addInterceptor(imageCorsInterceptor).addPathPatterns("/content/**");
    }
}