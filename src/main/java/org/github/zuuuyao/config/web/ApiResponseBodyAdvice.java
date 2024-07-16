package org.github.zuuuyao.config.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.github.zuuuyao.common.response.ApiResponse;
import org.github.zuuuyao.common.response.annotations.ApiIgnoreWrapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Api返回值包装
 *
 * @Desc Api返回值包装
 * @Time 2024-07-11 16:23
 * @Author HuangZhongYao
 */
@Slf4j
@RestControllerAdvice
public class ApiResponseBodyAdvice implements ResponseBodyAdvice<Object> {


    ObjectMapper jacksonObjectMapper = new ObjectMapper();

    /**
     * 需要包装的Controller,
     */
    private static final List<String> HANDLE_CONTROLLER_PACKAGE = Arrays.asList(
        "org.github.zuuuyao.web"
    );

    /**
     * 排除需要不需要包装的Controller(如果你集成其它依赖并且它有Controller)
     */
    private static final List<String> NOT_HANDLE = Arrays.asList(
        "org.springdoc.webmvc.api.OpenApiWebMvcResource",
        "org.springdoc.webmvc.ui.SwaggerWelcomeActuator",
        "org.springdoc.webmvc.ui.SwaggerWelcomeWebMvc",
        "org.springdoc.webmvc.ui.SwaggerUiHome",
        "org.springdoc.webmvc.ui.SwaggerConfigResource",
        "org.springdoc.webmvc.api.MultipleOpenApiWebMvcResource",
        "springfox.documentation.swagger.web.ApiResourceController",
        "springfox.documentation.swagger2.web.Swagger2Controller",
        "com.github.xiaoymin.swaggerbootstrapui.web.SwaggerBootstrapUiController");

    /**
     * 实现是否对API接口返回值包装逻辑
     *
     * @param returnType    the return type
     * @param converterType the selected converter type
     * @return 返回false则不包装, 返回true则包装
     */
    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {

        Class<?> declaringClass = returnType.getDeclaringClass();

        /**
         // 排除以前写的是对所有Controller进行包装，然后在这里来排除
         if (declaringClass.equals(BasicErrorController.class)) {
         return false;
         }
         return !NOT_HANDLE.contains(declaringClass.getName());
         */

        // 获取Controller上的注解,如Controller类上使用@ApiIgnoreWrapper忽略包装则跳过
        ApiIgnoreWrapper typeApiIgnoreWrapper =
            declaringClass.getAnnotation(ApiIgnoreWrapper.class);
        if (typeApiIgnoreWrapper != null) {
            return false;
        }

        // 获取Controller方法上的注解,如Controller方法上使用@ApiIgnoreWrapper忽略包装则跳过
        ApiIgnoreWrapper methodApiIgnoreWrapper =
            returnType.getMethodAnnotation(ApiIgnoreWrapper.class);
        if (methodApiIgnoreWrapper != null) {
            return false;
        }

        // 为了不影响其它的Controller,只对我们配置的controller包进行包装
        for (String packageName : HANDLE_CONTROLLER_PACKAGE) {
            if (declaringClass.getPackageName().equals(packageName)) {
                return true;
            }
        }

        return false;
    }


    /**
     * API响应结果包装
     *
     * @param body                  the body to be written
     * @param returnType            the return type of the controller method
     * @param selectedContentType   the content type selected through content negotiation
     * @param selectedConverterType the converter type selected to write to the response
     * @param request               the current request
     * @param response              the current response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {

        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setCode(200);
        apiResponse.setPath(request.getURI().getPath());
        apiResponse.setSuccess(Boolean.TRUE);
        apiResponse.setMessage("操作成功");
        apiResponse.setResult(body);

        // 如果返回值是字符串
        if (selectedConverterType.equals(StringHttpMessageConverter.class)) {
            return apiResponse.toString();
        }

        return apiResponse;
    }
}
