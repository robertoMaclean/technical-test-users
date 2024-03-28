package cl.technicaltest.password.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Password {
    @Id
    private Long id;
    private String regex;
    int minLength;
    int maxLength;
    boolean requiresUpperCase;
    boolean requiresLowerCase;
    boolean requiresDigit;
    boolean requiresSpecialChar;

    public Password() { }

    public Password(String regex, int minLength, int maxLength, boolean requiresUpperCase, boolean requiresLowerCase,
                    boolean requiresDigit, boolean requiresSpecialChar) {
        this.regex = regex;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.requiresUpperCase = requiresUpperCase;
        this.requiresLowerCase = requiresLowerCase;
        this.requiresDigit = requiresDigit;
        this.requiresSpecialChar = requiresSpecialChar;
    }

    public String getRegex() {
        return regex;
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

    public void setId(Long id) {
        this.id = id;
    }
}
