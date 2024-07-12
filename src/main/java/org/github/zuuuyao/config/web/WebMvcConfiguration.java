package org.github.zuuuyao.config.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvc配置类
 * @Desc WebMvc配置类
 * @Time 2024-07-11 16:23
 * @Author HuangZhongYao
 */
@Slf4j
@Component
public class WebMvcConfiguration implements WebMvcConfigurer {


    /**
     * 跨域配置
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        WebMvcConfigurer.super.addCorsMappings(registry);
        log.info("配置跨域处理.........");
        registry.addMapping("/**")
            .allowedOriginPatterns("*")
            .allowedHeaders("*")
            .allowedMethods("*")
            .maxAge(3600)
            .allowCredentials(true);

    }

    /**
     * 配置静态资源的处理规则
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        // 静态资源目录
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

        // webjars
        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
