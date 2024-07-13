package org.github.zuuuyao.common.validate.impl;

import cn.hutool.core.lang.PatternPool;
import cn.hutool.core.util.StrUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.github.zuuuyao.common.validate.ValidatePlateNum;



public class PlateNumValidatorImpl implements ConstraintValidator<ValidatePlateNum, Object> {

    boolean required;

    public PlateNumValidatorImpl() {
    }

    @Override
    public void initialize(ValidatePlateNum constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        if (!this.required && (null == o || StrUtil.isBlank(o.toString()))) {
            return true;
        }

        if (o instanceof String) {
            return PatternPool.PLATE_NUMBER.matcher(o.toString()).matches();
        }
        return false;
    }
}
