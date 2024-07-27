package com.fpt.fsa.employee_management.validators.echeck;

import com.fpt.fsa.employee_management.exceptions.EnumIncorrectException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class EnumSubsetValidator implements ConstraintValidator<EnumSubset, Enum<?>> {
    private String[] subset;

    @Override
    public void initialize(EnumSubset constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        if (value == null || value.toString().isEmpty() || !Arrays.asList(subset).contains(value.name())) {
            throw new EnumIncorrectException();
        }
        return true;
    }
}
