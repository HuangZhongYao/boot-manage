package org.github.zuuuyao.common.validate.impl;

import cn.hutool.core.lang.PatternPool;
import cn.hutool.core.util.StrUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.github.zuuuyao.common.validate.ValidateIP;


public class IPValidatorImpl implements ConstraintValidator<ValidateIP, Object> {

    boolean required;

    public IPValidatorImpl() {
    }

    @Override
    public void initialize(ValidateIP constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (!this.required && (null == o || StrUtil.isBlank(o.toString()))) {
            return true;
        }
        if (o instanceof String) {
            return PatternPool.IPV4.matcher(o.toString()).matches() || PatternPool.IPV6.matcher(o.toString()).matches();
        }
        return false;
    }
}
