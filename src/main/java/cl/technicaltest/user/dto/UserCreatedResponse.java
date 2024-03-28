package cl.technicaltest.user.dto;

import java.time.LocalDateTime;

public record UserCreatedResponse(
        String id,
        LocalDateTime created,
        LocalDateTime modified,
        LocalDateTime lastLogin,
        String token,
        boolean isActive
) {
}
