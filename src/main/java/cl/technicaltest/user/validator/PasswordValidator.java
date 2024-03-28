package cl.technicaltest.user.validator;


public class PasswordValidator {

    public boolean isValid(String password, String regex) {
        return password != null && password.matches(regex);
    }
}
