package org.github.zuuuyao.common.validate.impl;

import cn.hutool.core.lang.PatternPool;
import cn.hutool.core.util.StrUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.github.zuuuyao.common.validate.ValidateUnifiedSocialCreditIdentifier;



public class UnifiedSocialCreditIdentifierValidatorImpl
    implements ConstraintValidator<ValidateUnifiedSocialCreditIdentifier, Object> {

    boolean required;

    public UnifiedSocialCreditIdentifierValidatorImpl() {
    }

    @Override
    public void initialize(ValidateUnifiedSocialCreditIdentifier constraintAnnotation) {
        this.required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        if (!this.required && (null == o || StrUtil.isBlank(o.toString()))) {
            return true;
        }

        if (o instanceof String) {
            return PatternPool.CREDIT_CODE.matcher(o.toString()).matches();
        }
        return false;
    }
}
