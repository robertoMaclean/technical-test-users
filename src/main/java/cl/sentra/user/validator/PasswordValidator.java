package cl.sentra.user.validator;


public class PasswordValidator {

    public boolean isValid(String password, String regex) {
        // Perform validation using the provided regular expression
        return password != null && password.matches(regex);
    }
}
