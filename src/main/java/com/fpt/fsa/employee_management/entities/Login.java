package com.fpt.fsa.employee_management.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record Login (@NotBlank @NotEmpty @NotNull String account,
                     @NotBlank @NotEmpty @NotNull String password) {
}
