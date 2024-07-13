package org.github.zuuuyao.common.validate.impl;

import cn.hutool.core.util.PhoneUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.github.zuuuyao.common.validate.ValidatePhone;



public class PhoneValidatorImpl implements ConstraintValidator<ValidatePhone, Object> {

    boolean required;

    public PhoneValidatorImpl() {
    }

    @Override
    public void initialize(ValidatePhone constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        if (this.required == false && (null == o || StrUtil.isBlank(o.toString()))) {
            return true;
        }

        if (o instanceof String) {
            return PhoneUtil.isMobile(o.toString());
        }
        return false;
    }
}
