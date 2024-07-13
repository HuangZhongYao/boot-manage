package org.github.zuuuyao.common.validate.impl;

import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.github.zuuuyao.common.validate.ValidateIdCard;



public class IDCardValidatorImpl implements ConstraintValidator<ValidateIdCard, Object> {

    boolean required;

    public IDCardValidatorImpl() {
    }

    @Override
    public void initialize(ValidateIdCard constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (!this.required && (null == o || StrUtil.isBlank(o.toString()))) {
            return true;
        }
        if (o instanceof String) {
            return IdcardUtil.isValidCard(o.toString());
        }
        return false;
    }
}
