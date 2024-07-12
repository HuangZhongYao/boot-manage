package org.github.zuuuyao.config.web;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.github.zuuuyao.common.exception.UserFriendlyException;
import org.github.zuuuyao.common.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常处理
 * @Desc 全局异常处理
 * @Time 2024-07-11 16:23
 * @Author HuangZhongYao
 */
@Slf4j
@RestControllerAdvice
public class GlobalErrorController {


    /**
     * 统一处理业务中抛出的用户友好异常
     *
     * @param e        异常对象
     * @param request  请求对象
     * @param response 响应对象
     * @return 响应信息
     */
    @ExceptionHandler(value = UserFriendlyException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ErrorResponse userFriendlyExceptionHandler(UserFriendlyException e,
                                                      HttpServletRequest request,
                                                      HttpServletResponse response) {

        // 构建返回信息
        ErrorResponse errorResponse = this.buildErrorResponse(e, request);
        errorResponse.setCode(e.getCode());
        errorResponse.setMsg(e.getMessage());
        errorResponse.setPath(request.getRequestURI());


        StackTraceElement[] stackTrace = e.getStackTrace();
        StackTraceElement stackTraceElement = stackTrace[0];
        // 打印日志
        log.error("用户友好异常提示信息: {}. 在 {}.{}()方法中第 {} 行. ", e.getMessage(),
            stackTraceElement.getClassName(), stackTraceElement.getMethodName(),
            stackTraceElement.getLineNumber());
        return errorResponse;
    }

    /**
     * http请求方法不支持处理
     *
     * @param request   请求对象
     * @param response  响应对象
     * @param exception 异常对象
     * @return 异常信息
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleHttpRequestMethodNotSupported(HttpServletRequest request,
                                                             HttpServletResponse response,
                                                             HttpRequestMethodNotSupportedException exception) {
        ErrorResponse errorResponse = this.buildErrorResponse(exception, request);

        String msg = String.format("%s 该接口不支持 %s,请使用 %s ", errorResponse.getPath(),
            exception.getMethod(),
            Arrays.toString(exception.getSupportedMethods()));

        errorResponse.setMsg(msg);
        errorResponse.setCode(ErrorResponse.FAILED);

        // 打印日志
        log.error("{} ", msg);

        return errorResponse;
    }


    private ErrorResponse buildErrorResponse(Exception e, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMsg(e.getMessage());
        errorResponse.setPath(request.getRequestURI());
        return errorResponse;
    }

}

