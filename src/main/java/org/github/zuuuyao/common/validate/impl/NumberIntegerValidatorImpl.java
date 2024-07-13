package org.github.zuuuyao.common.validate.impl;

import cn.hutool.core.lang.PatternPool;
import cn.hutool.core.util.StrUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.github.zuuuyao.common.validate.ValidateNumberInteger;



public class NumberIntegerValidatorImpl implements
    ConstraintValidator<ValidateNumberInteger, Object> {

    boolean required;

    public NumberIntegerValidatorImpl() {
    }

    @Override
    public void initialize(ValidateNumberInteger constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (!this.required && (null == o || StrUtil.isBlank(o.toString()))) {
            return true;
        }

        if (o instanceof String || o instanceof Number) {
            return PatternPool.NUMBERS.matcher(o.toString()).matches();
        }
        return false;
    }
}
