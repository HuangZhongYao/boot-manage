package org.github.zuuuyao.config.security;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.router.SaRouter;
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
        // 注册 Sa-Token 拦截器
        log.info("加载 Sa-Token 拦截器中...");

        // TODO 基于路由鉴权（官方文档https://sa-token.cc/doc.html#/use/route-check）
        registry
                .addInterceptor(new SaInterceptor(handle -> {

                    // TODO 后端鉴权就不控制到每个API了因为我想写的是轻量级的框架，要是控制到每个api前端配置权限的时候增加复杂度。
                    //  要实现也是很简单基于注解鉴权和SpringSecurity一样
                    //  在接口上添加鉴权注解,参考官方文档(https://sa-token.cc/doc.html#/use/at-check)

                    // 匹配拦截的所有请求做登录校验 (这里只做是否登录校验)
                    SaRouter.match("/**", r -> StpUtil.checkLogin());

                    // 可以根据路由划分模块，不同模块不同鉴权
                    // SaRouter.match("/user/**", r -> StpUtil.checkPermission("user"));
                    // SaRouter.match("/role/**", r -> StpUtil.checkPermission("admin"));

                }))
                // 拦截所有请求
                .addPathPatterns("/**")
                // 放行的请求
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
