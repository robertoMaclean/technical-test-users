package cl.technicaltest.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record PhoneRequest(
        @Schema(description = "Phone number", example = "+56987878752")
        String number,
        @Schema(description = "City code", example = "12")
        String citycode,
        @Schema(description = "Country code", example = "54")
        String countrycode
) {}

