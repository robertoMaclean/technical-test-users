package cl.sentra.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class PhoneRequest {
    @Schema(example = "+56987878752")
    private String number;
    @Schema(example = "12")
    private String citycode;
    @Schema(example = "54")
    private String countrycode;

    public String getNumber() {
        return number;
    }

    public String getCitycode() {
        return citycode;
    }

    public String getCountrycode() {
        return countrycode;
    }

}
