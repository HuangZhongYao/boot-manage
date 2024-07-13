package org.github.zuuuyao.common.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.github.zuuuyao.common.validate.impl.WordValidatorImpl;


/**
 * 验证字母只允许为大小写字母,可以出现空格
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
        validatedBy = {WordValidatorImpl.class}
)
public @interface ValidateWord {
    String message() default "只允许为大小写字母";

    /**
     * 验证参数是否为必须的
     *
     * @return
     */
    boolean required() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
