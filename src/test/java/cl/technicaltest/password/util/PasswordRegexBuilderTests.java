package cl.technicaltest.password.util;

import cl.technicaltest.user.validator.PasswordValidator;
import org.junit.jupiter.api.Test;

public class PasswordRegexBuilderTests {

    @Test
    public void isPasswordMinLenghtValid() {
        String password = "valid";
        String minLengthRegex = new PasswordRegexBuilder().minLength(3).build();
        assert passwordIsValid(password, minLengthRegex);
    }

    @Test
    public void isPasswordMinLenghtInvalid() {
        String password = "invalid";
        String minLengthRegex = new PasswordRegexBuilder().minLength(10).build();
        assert !passwordIsValid(password, minLengthRegex);
    }

    @Test
    public void isPasswordMaxLenghtValid() {
        String password = "valid";
        String minLengthRegex = new PasswordRegexBuilder().maxLength(5).build();
        assert passwordIsValid(password, minLengthRegex);
    }

    @Test
    public void isPasswordMaxLenghtInvalid() {
        String password = "invalid";
        String minLengthRegex = new PasswordRegexBuilder().maxLength(5).build();
        assert !passwordIsValid(password, minLengthRegex);
    }

    @Test
    public void isPasswordWithLowerCaseValid() {
        String password = "valid";
        String minLengthRegex = new PasswordRegexBuilder().requiresLowerCase(true).build();
        assert passwordIsValid(password, minLengthRegex);
    }

    @Test
    public void isPasswordWithLowerCaseInvalid() {
        String password = "INVALID";
        String minLengthRegex = new PasswordRegexBuilder().requiresLowerCase(true).build();
        assert !passwordIsValid(password, minLengthRegex);
    }

    @Test
    public void isPasswordWithUpperCaseValid() {
        String password = "Valid";
        String minLengthRegex = new PasswordRegexBuilder().requiresUpperCase(true).build();
        assert passwordIsValid(password, minLengthRegex);
    }

    @Test
    public void isPasswordWithUpperCaseInvalid() {
        String password = "invalid";
        String minLengthRegex = new PasswordRegexBuilder().requiresUpperCase(true).build();
        assert !passwordIsValid(password, minLengthRegex);
    }

    @Test
    public void isPasswordWithDigitValid() {
        String password = "valid1";
        String minLengthRegex = new PasswordRegexBuilder().requiresDigit(true).build();
        assert passwordIsValid(password, minLengthRegex);
    }

    @Test
    public void isPasswordWithDigitInvalid() {
        String password = "invalid";
        String minLengthRegex = new PasswordRegexBuilder().requiresDigit(true).build();
        assert !passwordIsValid(password, minLengthRegex);
    }

    @Test
    public void isPasswordWithSpecialCharValid() {
        String password = "valid#";
        String minLengthRegex = new PasswordRegexBuilder().requiresSpecialChar(true).build();
        assert passwordIsValid(password, minLengthRegex);
    }

    @Test
    public void isPasswordWithSpecialCharInvalid() {
        String password = "invalid";
        String minLengthRegex = new PasswordRegexBuilder().requiresSpecialChar(true).build();
        assert !passwordIsValid(password, minLengthRegex);
    }

    @Test
    public void isPasswordWithUpperCaseAndSpecialCharValid() {
        String password = "VALID#";
        String minLengthRegex = new PasswordRegexBuilder()
                .requiresSpecialChar(true)
                .requiresUpperCase(true)
                .build();
        assert passwordIsValid(password, minLengthRegex);
    }

    @Test
    public void isPasswordWithUpperCaseSpecialCharAndLowerCaseValid() {
        String password = "ValiD#";
        String minLengthRegex = new PasswordRegexBuilder()
                .requiresSpecialChar(true)
                .requiresUpperCase(true)
                .requiresLowerCase(true)
                .build();
        assert passwordIsValid(password, minLengthRegex);
    }

    @Test
    public void isPasswordWithUpperCaseSpecialCharLowerCaseAndDigitValid() {
        String password = "ValidD#1";
        String minLengthRegex = new PasswordRegexBuilder()
                .requiresSpecialChar(true)
                .requiresUpperCase(true)
                .requiresLowerCase(true)
                .requiresDigit(true)
                .build();
        assert passwordIsValid(password, minLengthRegex);
    }

    private boolean passwordIsValid(String password, String regex) {
        PasswordValidator passwordValidator = new PasswordValidator();
        return passwordValidator.isValid(password, regex);
    }
}
