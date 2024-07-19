package org.github.zuuuyao.config.swagger;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

/**
 * swagger配置类
 *
 * @Desc
 * @Time 2024-07-11 16:23
 * @Author HuangZhongYao
 */

@OpenAPIDefinition(
        info = @Info(
                title = "Boot-Manage 后台管理系统单体架构Boot版 API文档",
                version = "1.0.0",
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

}
