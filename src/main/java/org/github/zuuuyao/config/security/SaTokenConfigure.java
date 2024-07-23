package org.github.zuuuyao.config.security;

import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.stp.StpLogic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * satoken认证配置类
 *
 * @Desc
 * @Time 2024-07-11 16:23
 * @Author HuangZhongYao
 */
@Slf4j
@Configuration
public class SaTokenConfigure {

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
}
