package org.github.zuuuyao.common.validate.impl;

import cn.hutool.core.util.StrUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.github.zuuuyao.common.validate.ValidateQQ;



public class QQValidatorImpl implements ConstraintValidator<ValidateQQ, Object> {

    private static final String regexp = "[1-9]([0-9]{5,11})";

    boolean required;

    public QQValidatorImpl() {
    }

    @Override
    public void initialize(ValidateQQ constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        if (!this.required && (null == o || StrUtil.isBlank(o.toString()))) {
            return true;
        }

        if (o instanceof String) {
            return o.toString().matches(regexp);
        }
        return false;
    }
}
