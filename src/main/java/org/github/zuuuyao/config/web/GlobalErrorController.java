package org.github.zuuuyao.config.web;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.github.zuuuyao.common.exception.UserFriendlyException;
import org.github.zuuuyao.common.response.ErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 全局异常处理
 *
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
     * @param exception 异常对象
     * @param request   请求对象
     * @param response  响应对象
     * @return 响应信息
     */
    @ExceptionHandler(value = UserFriendlyException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ErrorResponse userFriendlyExceptionHandler(UserFriendlyException exception, HttpServletRequest request, HttpServletResponse response) {

        // 构建返回信息
        ErrorResponse errorResponse = this.buildErrorResponse(exception, request);
        errorResponse.setCode(exception.getCode());
        errorResponse.setMessage(exception.getMessage());

        StackTraceElement[] stackTrace = exception.getStackTrace();
        StackTraceElement stackTraceElement = stackTrace[0];

        // 打印日志
        log.error("用户友好异常提示信息: {}. 在 {}.{}()方法中第 {} 行. ", exception.getMessage(), stackTraceElement.getClassName(), stackTraceElement.getMethodName(), stackTraceElement.getLineNumber());
        return errorResponse;
    }

    /**
     * 全局处理validation验证不通过异常
     *
     * @param request   请求对象
     * @param response  响应对象
     * @param exception 异常对象
     * @return 响应对象
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public ErrorResponse handelMethodArgumentNotValidException(HttpServletRequest request, HttpServletResponse response, MethodArgumentNotValidException exception) {

        // 构建错误信息
        ErrorResponse errorResponse = this.buildErrorResponse(exception, request);
        // 获取未验证通过抛出的全部异常
        List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
        // 获取全部异常的message用 ';' 拼接为字符串
        String errorMsg = allErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(";"));
        errorResponse.setMessage(errorMsg);
        errorResponse.setCode(ErrorResponse.VALIDATION_FAILED);

        return errorResponse;
    }

    /**
     * 处理 @RequestParam 参数验证不通过
     * @return 异常信息
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.OK)
    public ErrorResponse handelMissingServletRequestParameterException(HttpServletRequest request, HttpServletResponse response, MissingServletRequestParameterException exception) {
        ErrorResponse errorResponse = this.buildErrorResponse(exception, request);
        errorResponse.setCode(ErrorResponse.VALIDATION_FAILED);
        errorResponse.setMessage(String.format("%s , 不能为空!",exception.getParameterName()));
        return errorResponse;
    }

    /**
     * http 405 请求方法不支持处理
     *
     * @param request   请求对象
     * @param response  响应对象
     * @param exception 异常对象
     * @return 异常信息
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleHttpRequestMethodNotSupported(HttpServletRequest request, HttpServletResponse response, HttpRequestMethodNotSupportedException exception) {

        String msg = String.format("%s 该接口不支持 %s,请使用 %s ", request.getRequestURI(), exception.getMethod(), Arrays.toString(exception.getSupportedMethods()));

        ErrorResponse errorResponse = this.buildErrorResponse(exception, request);
        errorResponse.setMessage(msg);
        errorResponse.setCode(ErrorResponse.FAILED);
        // 打印日志
        log.error("{} ", msg);
        return errorResponse;
    }


    private ErrorResponse buildErrorResponse(Exception e, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setPath(request.getRequestURI());
        return errorResponse;
    }

}

