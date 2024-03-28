package cl.technicaltest.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UserRequest(
        @Schema(description = "User name", example = "Juan Rodriguez")
        @NotNull(message = "Name cannot be null")
        @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
        String name,
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$", message = "Invalid email address")
        @NotNull(message = "Email cannot be null")
        @Schema(description = "User email", example = "juan@rodriguez.org")
        String email,
        @Schema(description = "User password", example = "hunter2")
        @NotNull(message = "Name cannot be null")
        String password,
        List<PhoneRequest> phones
) {}


