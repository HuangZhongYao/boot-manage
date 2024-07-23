package org.github.zuuuyao.common.response;

import lombok.Getter;

/**
 * 响应状态码枚举
 *
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-23 19:59
 */
@Getter
public enum ResponseCode {
    OK(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATION_FAILED(410, "参数验证失败"),
    NOT_LOGIN(401, "未登录"),
    LOGIN_EXPIRED(401, "登录过期"),
    NO_PERMISSION(403, "没有操作权限"),
    ;

    /**
     * 状态码
     */
    public int code;

    /**
     * 消息描述
     */
    public String message;

    ResponseCode() {
    }

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
