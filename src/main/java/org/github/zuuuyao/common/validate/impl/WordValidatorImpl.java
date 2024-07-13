package org.github.zuuuyao.common.validate.impl;

import cn.hutool.core.lang.PatternPool;
import cn.hutool.core.util.StrUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.NotNull;
import org.github.zuuuyao.common.validate.ValidateWord;



public class WordValidatorImpl implements ConstraintValidator<ValidateWord, Object> {

    private static final String regexp = "[\\u4e00-\\u9fa5]+";

    boolean required;

    public WordValidatorImpl() {
    }

    @Override
    public void initialize(ValidateWord constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        if (!this.required && (null == o || StrUtil.isBlank(o.toString()))) {
            return true;
        }

        if (o instanceof String) {
            return PatternPool.WORD.matcher(o.toString()).matches();
        }
        return false;
    }
}
