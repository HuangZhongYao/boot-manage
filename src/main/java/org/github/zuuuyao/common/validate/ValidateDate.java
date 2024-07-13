package org.github.zuuuyao.common.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.github.zuuuyao.common.validate.impl.DateValidatorImpl;

/**
 * 验证时间格式，只能是日期格式
 *
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-13 15:38
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {DateValidatorImpl.class}
)
public @interface ValidateDate {
    String message() default "日期格式不正确";

    /**
     * 有效的日期格式
     */
    String[] format() default {};


    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
