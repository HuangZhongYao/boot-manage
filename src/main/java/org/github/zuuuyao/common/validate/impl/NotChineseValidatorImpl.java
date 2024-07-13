package org.github.zuuuyao.common.validate.impl;

import cn.hutool.core.util.StrUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;
import org.github.zuuuyao.common.validate.ValidateNotChinese;


public class NotChineseValidatorImpl implements ConstraintValidator<ValidateNotChinese, Object> {

    private static final Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
    boolean required;

    public NotChineseValidatorImpl() {
    }

    @Override
    public void initialize(ValidateNotChinese constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (!this.required && (null == o || StrUtil.isBlank(o.toString()))) {
            return true;
        }
        if (o instanceof String) {
            return !pattern.matcher(o.toString()).find();
        }
        return false;
    }
}
