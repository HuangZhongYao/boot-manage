package org.github.zuuuyao.config.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis-plus配置类
 *
 * @Desc mybatis-plus配置类
 * @Time 2024-07-11 16:22
 * @Author HuangZhongYao
 */
@MapperScan("org.github.zuuuyao.repository")
@Configuration
@EnableTransactionManagement
public class MybatisPlusConfiguration {

    public MybatisPlusConfiguration() {
    }

    private static final Logger log = LoggerFactory.getLogger(MybatisPlusConfiguration.class);

    /**
     * 注册mybatis-plus拦截器
     *
     * @return MybatisPlusInterceptor
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        // mybatis-plus拦截器
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        log.info("Mybatis-plus拦截器加载...");

        // 添加非法SQL拦截器
        // log.info("Mybatis-plus非法SQL拦截插件加载...");
        // interceptor.addInnerInterceptor(new IllegalSQLInnerInterceptor());

        // 如果配置多个插件, 切记分页最后添加
        // 如果有多数据源可以不配具体类型, 否则都建议配上具体的 DbType
        log.info("Mybatis-plus分页插件加载...");
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
