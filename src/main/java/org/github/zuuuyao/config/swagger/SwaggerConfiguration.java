package org.github.zuuuyao.config.swagger;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

/**
 * @Desc
 * @Time 2024-07-11 16:23
 * @Author HuangZhongYao
 */

@OpenAPIDefinition(
        info = @Info(
                title = "Boot-Mange API 文档",
                version = "1.0.0",
                description = "Boot-Mange API 文档的详细描述",
                termsOfService = "http://example.com/terms",

                license = @License(
                        name = "许可证名称",
                        url = "http://example.com/license"
                ),
                contact = @Contact(name = "HZY", url = "wwww.hzy.com", email = "78438@163.com")
        )
)
@Configuration
public class SwaggerConfiguration {

}
