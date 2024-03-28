package cl.technicaltest.password.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record PasswordCreateRequest(
        @Min(3)
        int minLength,
        @Max(30)
        int maxLength,
        boolean requiresUpperCase,
        boolean requiresLowerCase,
        boolean requiresDigit,
        boolean requiresSpecialChar
    ) { }
