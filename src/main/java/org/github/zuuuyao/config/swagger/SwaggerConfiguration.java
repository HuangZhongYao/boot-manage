package org.github.zuuuyao.config.swagger;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

/**
 * swagger配置类
 *
 * @Desc
 * @Time 2024-07-11 16:23
 * @Author HuangZhongYao
 */
@io.swagger.v3.oas.annotations.security.SecurityScheme(name = "Authorization", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER, description = "JWT Bearer Token 认证", scheme = "token")
@OpenAPIDefinition(
        info = @Info(
                title = "Boot-Manage 后台管理系统单体架构Boot版 API文档",
                description = "Boot-Manage后台管理系统中 所涉及的API文档的详细描述",
                termsOfService = "http://example.com/terms",
                license = @License(
                        name = "许可证名称",
                        url = "http://example.com/license"
                ),
                contact = @Contact(name = "HuangZhongYao", url = "https://github.com/HuangZhongYao/boot-manage", email = "176853006043@163.com")
        )
)
@Configuration
public class SwaggerConfiguration {

    /**
     * 全局自定义扩展
     */
    @Bean
    public GlobalOpenApiCustomizer globalOpenApiCustomizer() {
        return openApi -> {
            // 全局添加Authorization
            if (openApi.getPaths() != null) {
                openApi.getPaths().forEach((path, pathItem) -> {
                    // 其他接口统一添加Authorization
                    pathItem.readOperations()
                            .forEach(operation ->
                                    operation.addSecurityItem(new SecurityRequirement().addList(HttpHeaders.AUTHORIZATION))
                            );
                });
            }
        };
    }

}
