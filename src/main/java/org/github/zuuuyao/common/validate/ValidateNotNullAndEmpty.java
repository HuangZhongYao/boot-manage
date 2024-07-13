package org.github.zuuuyao.common.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.github.zuuuyao.common.validate.impl.NotNullAndEmptyValidatorImpl;

/**
 * 验证不能为空或者null
 * @Desc: Created by IntelliJ IDEA.
 * @Author: ZhongYao.Huang
 * @Copyright: ZuuuuYao By Github
 * @Time: 2024-07-13 15:38
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {NotNullAndEmptyValidatorImpl.class}
)
public @interface ValidateNotNullAndEmpty {

    String message() default "不可为Null或空";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
