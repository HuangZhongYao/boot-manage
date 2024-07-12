package org.github.zuuuyao.common.exception;

import java.io.Serial;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户友好异常用于提示信息
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-12 23:19
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class UserFriendlyException extends BaseException{

    @Serial
    private static final long serialVersionUID = -6118175135478623791L;

    public UserFriendlyException() {
    }


    public UserFriendlyException(String message, int code) {
        super(message, code);
    }

    public UserFriendlyException(String message) {
        super(message, 500);
    }

    public UserFriendlyException(String message, Throwable cause, int code) {
        super(message, cause, code);
    }

    public UserFriendlyException(Throwable cause, int code) {
        super(cause, code);
    }

    public UserFriendlyException(String message, Throwable cause, boolean enableSuppression,
                                 boolean writableStackTrace, int code) {
        super(message, cause, enableSuppression, writableStackTrace, code);
    }
}
