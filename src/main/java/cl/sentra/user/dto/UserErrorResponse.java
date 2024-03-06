package cl.sentra.user.dto;

public class UserErrorResponse {

    private final String message;

    public UserErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
