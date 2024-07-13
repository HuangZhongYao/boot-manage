package org.github.zuuuyao.common.validate.impl;

import cn.hutool.core.lang.PatternPool;
import cn.hutool.core.util.StrUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.github.zuuuyao.common.validate.ValidateURL;



public class URLValidatorImpl implements ConstraintValidator<ValidateURL, Object> {

    private static final String regexp = "^((https|http|ftp|rtsp|mms)?:\\/\\/)[^\\s]+";

    boolean required;

    public URLValidatorImpl() {
    }

    @Override
    public void initialize(ValidateURL constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        if (!this.required && (null == o || StrUtil.isBlank(o.toString()))) {
            return true;
        }

        if (o instanceof String) {
            return PatternPool.URL.matcher(o.toString()).matches();
        }
        return false;
    }
}
