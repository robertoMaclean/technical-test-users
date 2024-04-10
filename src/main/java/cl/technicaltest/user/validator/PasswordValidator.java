package cl.technicaltest.user.validator;


public class PasswordValidator {

    public static boolean isValid(String password, String regex) {
        return password != null && password.matches(regex);
    }
}
