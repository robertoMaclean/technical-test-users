package cl.sentra.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Pattern;
import java.util.List;

public class UserRequest {

    @Schema(example = "Juan Rodriguez")
    private String name;
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$", message = "Invalid email address")
    @Schema(example = "juan@rodriguez.org")
    private String email;
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9]).{6,12}$",
            message = "Invalid password format")
    @Schema(example = "hunter2")
    private String password;
    private List<PhoneRequest> phones;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<PhoneRequest> getPhones() {
        return phones;
    }

}


