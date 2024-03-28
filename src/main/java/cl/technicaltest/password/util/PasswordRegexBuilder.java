package cl.technicaltest.password.util;

public class PasswordRegexBuilder {
    private int minLength = 4;
    private int maxLength = 10;
    private boolean requiresUpperCase = false;
    private boolean requiresLowerCase = false;
    private boolean requiresDigit = false;
    private boolean requiresSpecialChar = false;

    public PasswordRegexBuilder() {}

    public PasswordRegexBuilder minLength(int minLength) {
        this.minLength = minLength;
        return this;
    }

    public PasswordRegexBuilder maxLength(int maxLength) {
        this.maxLength = maxLength;
        return this;
    }

    public PasswordRegexBuilder requiresUpperCase(boolean requiresUpperCase) {
        this.requiresUpperCase = requiresUpperCase;
        return this;
    }

    public PasswordRegexBuilder requiresLowerCase(boolean requiresLowerCase) {
        this.requiresLowerCase = requiresLowerCase;
        return this;
    }

    public PasswordRegexBuilder requiresDigit(boolean requiresDigit) {
        this.requiresDigit = requiresDigit;
        return this;
    }

    public PasswordRegexBuilder requiresSpecialChar(boolean requiresSpecialChar) {
        this.requiresSpecialChar = requiresSpecialChar;
        return this;
    }

    public String build() {
        StringBuilder regex = new StringBuilder("^");
        if (requiresUpperCase) {
            regex.append("(?=.*[A-Z])");
        }
        if (requiresLowerCase) {
            regex.append("(?=.*[a-z])");
        }
        if (requiresDigit) {
            regex.append("(?=.*\\d)");
        }
        if (requiresSpecialChar) {
            regex.append("(?=.*[@#$%^&+=*])");
        }
        regex.append(".{").append(minLength).append(",").append(maxLength).append("}$");
        return regex.toString();
    }

    public int getMinLength() {
        return minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public boolean isRequiresUpperCase() {
        return requiresUpperCase;
    }

    public boolean isRequiresLowerCase() {
        return requiresLowerCase;
    }

    public boolean isRequiresDigit() {
        return requiresDigit;
    }

    public boolean isRequiresSpecialChar() {
        return requiresSpecialChar;
    }
}
