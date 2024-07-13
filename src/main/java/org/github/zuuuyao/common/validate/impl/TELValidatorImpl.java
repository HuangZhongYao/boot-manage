package org.github.zuuuyao.common.validate.impl;

import cn.hutool.core.util.StrUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.github.zuuuyao.common.validate.ValidateTEL;



public class TELValidatorImpl implements ConstraintValidator<ValidateTEL, Object> {


    private static final String regexp = "0\\d{2,3}[1-9]\\d{6,7}";

    boolean required;

    public TELValidatorImpl() {

    }

    @Override
    public void initialize(ValidateTEL constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        if (!this.required && (null == o || StrUtil.isBlank(o.toString()))) {
            return true;
        }

        if (o instanceof String) {
            String tel = o.toString().replaceAll("-", "");
            return tel.matches(regexp);
        }
        return false;
    }
}
