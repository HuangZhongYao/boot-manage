package org.github.zuuuyao.common.validate.impl;

import cn.hutool.core.util.StrUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;
import org.github.zuuuyao.common.validate.ValidateNumberFloat;



public class NumberFloatValidatorImpl implements ConstraintValidator<ValidateNumberFloat, Object> {

    private static final Pattern pattern = Pattern.compile("^\\d+\\.\\d+$");

    boolean required;

    public NumberFloatValidatorImpl() {
    }

    @Override
    public void initialize(ValidateNumberFloat constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (!this.required && (null == o || StrUtil.isBlank(o.toString()))) {
            return true;
        }
        if (o instanceof String || o instanceof Number) {
            return pattern.matcher(o.toString()).matches();
        }
        return false;
    }
}
