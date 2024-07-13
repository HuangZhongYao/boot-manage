package org.github.zuuuyao.common.validate.impl;

import cn.hutool.core.lang.PatternPool;
import cn.hutool.core.util.StrUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.github.zuuuyao.common.validate.ValidateChinese;


public class ChineseValidatorImpl implements ConstraintValidator<ValidateChinese, Object> {

    boolean required;

    public ChineseValidatorImpl() {
    }

    @Override
    public void initialize(ValidateChinese constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (!this.required && (null == o || StrUtil.isBlank(o.toString()))) {
            return true;
        }
        if (o instanceof String) {
            return PatternPool.CHINESES.matcher(o.toString()).matches();
        }
        return false;
    }
}
