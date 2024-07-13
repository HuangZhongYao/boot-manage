package org.github.zuuuyao.common.validate.impl;

import cn.hutool.core.util.StrUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;
import org.github.zuuuyao.common.validate.ValidateNumber;


public class NumberValidatorImpl implements ConstraintValidator<ValidateNumber, Object> {

    private static final Pattern pattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");

    boolean required;

    public NumberValidatorImpl() {
    }

    @Override
    public void initialize(ValidateNumber constraintAnnotation) {
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
