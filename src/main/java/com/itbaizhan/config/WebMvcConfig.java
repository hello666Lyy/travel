package com.itbaizhan.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 旧图片：映射static下所有资源（包括backstage、frontdesk、upload）
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");

        // 新上传图片：和你代码里的保存路径完全一致
        String uploadPath = System.getProperty("user.dir") + "/src/main/resources/static/upload/";
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:" + uploadPath);

        // 核心：/** 匹配所有请求路径，映射到 static 目录下的所有文件（包括子目录）
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
}