package cl.sentra.user.exception;

public class UserExistException extends RuntimeException {

    public UserExistException(String message) {
        super(message);
    }

}
