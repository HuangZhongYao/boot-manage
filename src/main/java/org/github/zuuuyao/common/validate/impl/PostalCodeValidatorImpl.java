package org.github.zuuuyao.common.validate.impl;

import cn.hutool.core.lang.PatternPool;
import cn.hutool.core.util.StrUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.github.zuuuyao.common.validate.ValidatePostalCode;



public class PostalCodeValidatorImpl implements ConstraintValidator<ValidatePostalCode, Object> {

    private static final String regexp = "\\d{6}";

    boolean required;

    public PostalCodeValidatorImpl() {
    }

    @Override
    public void initialize(ValidatePostalCode constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        if (!this.required && (null == o || StrUtil.isBlank(o.toString()))) {
            return true;
        }

        if (o instanceof String) {
            return PatternPool.ZIP_CODE.matcher(o.toString()).matches();
        }
        return false;
    }
}
