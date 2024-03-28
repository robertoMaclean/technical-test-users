package cl.technicaltest.password.service;

import cl.technicaltest.password.dto.PasswordCreateRequest;
import cl.technicaltest.password.model.Password;
import cl.technicaltest.password.repository.PasswordRepository;
import cl.technicaltest.password.util.PasswordRegexBuilder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private final PasswordRepository passwordRepository;

    public PasswordService(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    public Password createPassword(PasswordCreateRequest passwordCreateRequest) {
        return this.saveRegex(passwordCreateRequest);

    }
    private Password saveRegex(PasswordCreateRequest passwordCreateRequest) {
        Password password = createPasswordWithPasswordCreateRequest(passwordCreateRequest);
        password.setId(1L);
        return passwordRepository.save(password);
    }

    private Password createPasswordWithPasswordCreateRequest(PasswordCreateRequest passwordCreateRequest) {
        String regex = this.createRegex(passwordCreateRequest);
        return new Password(
                regex, passwordCreateRequest.minLength(),
                passwordCreateRequest.maxLength(),
                passwordCreateRequest.requiresUpperCase(),
                passwordCreateRequest.requiresLowerCase(),
                passwordCreateRequest.requiresDigit(),
                passwordCreateRequest.requiresSpecialChar());
    }

    private String createRegex(PasswordCreateRequest passwordCreateRequest) {
        return new PasswordRegexBuilder()
                .minLength(passwordCreateRequest.minLength())
                .maxLength(passwordCreateRequest.maxLength())
                .requiresUpperCase(passwordCreateRequest.requiresUpperCase())
                .requiresLowerCase(passwordCreateRequest.requiresLowerCase())
                .requiresDigit(passwordCreateRequest.requiresDigit())
                .requiresSpecialChar(passwordCreateRequest.requiresSpecialChar())
                .build();
    }

    public String getRegex() {
        Password password = passwordRepository.findById(1L).orElse(null);
        if(password == null) {
            Password newPassword = this.createDefaultPassword();
            newPassword.setId(1L);
            this.passwordRepository.save(newPassword);
            return newPassword.getRegex();
        }
        return password.getRegex();
    }

    private Password createDefaultPassword() {
        PasswordRegexBuilder passwordRegexBuilder = new PasswordRegexBuilder();
        String regex = passwordRegexBuilder.build();
        return new Password(regex,
                passwordRegexBuilder.getMinLength(),
                passwordRegexBuilder.getMaxLength(),
                passwordRegexBuilder.isRequiresUpperCase(),
                passwordRegexBuilder.isRequiresLowerCase(),
                passwordRegexBuilder.isRequiresDigit(),
                passwordRegexBuilder.isRequiresSpecialChar());
    }

    public String getValidationMessage() {
        Password password = passwordRepository.findById(1L).orElse(null);
        return password != null ? this.createValidationMessage(password): "";
    }

    private String createValidationMessage(Password password) {
        return "min length %s, max length %s%s%s%s%s".formatted(
                password.getMinLength(),
                password.getMaxLength(),
                password.isRequiresUpperCase() ? ", at least one uppercase": "",
                password.isRequiresLowerCase() ? ", at least one lowercase": "",
                password.isRequiresDigit() ? ", at least one digit": "",
                password.isRequiresSpecialChar() ? ", at least one special char": ""
        );
    }
}
