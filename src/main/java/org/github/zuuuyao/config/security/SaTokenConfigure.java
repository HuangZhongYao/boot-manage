package org.github.zuuuyao.config.security;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * satoken认证配置类
 *
 * @Desc
 * @Time 2024-07-11 16:23
 * @Author HuangZhongYao
 */
@Slf4j
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    /**
     * Sa-Token 整合 jwt (Stateless 无状态模式)
     *
     * @return StpLogic
     */
    @Bean
    public StpLogic getStpLogicJwt() {
        log.info("加载 saToken JWT 插件中...");
        return new StpLogicJwtForSimple();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
        log.info("加载 Sa-Token 拦截器中...");
        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()))
            .addPathPatterns("/**")
            // 放行请求
            .excludePathPatterns(
                "/error",
                "/auth/login",
                "/auth/logout",
                "/auth/captcha",
                "/doc.html",
                "/swagger-ui/**",
                "/v3/**",
                "/static/**",
                "/webjars/**",
                "/swagger-ui*/**");
    }
}
