package org.github.zuuuyao.common.validate.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.github.zuuuyao.common.validate.ValidateNotNullAndEmpty;
import org.springframework.web.multipart.MultipartFile;


public class NotNullAndEmptyValidatorImpl
    implements ConstraintValidator<ValidateNotNullAndEmpty, Object> {
    public NotNullAndEmptyValidatorImpl() {
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (o instanceof List) {
            return !((List<?>) o).isEmpty();
        } else if (o instanceof Set) {
            return !((Set<?>) o).isEmpty();
        } else if (o instanceof String) {
            return !o.toString().isEmpty();
        } else if (o instanceof MultipartFile) {
            return !((MultipartFile) o).isEmpty();
        } else {
            return o instanceof Long || o instanceof Integer || o instanceof BigDecimal ||
                o instanceof Boolean || o instanceof Double || o instanceof Float ||
                o instanceof Enum || o instanceof Date;
        }
    }

    @Override
    public void initialize(ValidateNotNullAndEmpty constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

}
