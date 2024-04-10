package cl.technicaltest.user.validator;

import cl.technicaltest.password.service.PasswordService;
import cl.technicaltest.user.dto.UserRequest;
import cl.technicaltest.user.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import java.util.stream.Collectors;

@Component
public class UserRequestValidator {

    private final PasswordService passwordService;

    @Autowired
    public UserRequestValidator(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    public void validateCreateUserRequest(BindingResult bindingResult, UserRequest userRequest) {
        if(bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));
            throw new BadRequestException(errorMessage);
        }
        if(!PasswordValidator.isValid(userRequest.password(), passwordService.getRegex())) {
            throw new BadRequestException("Invalid password format: " + passwordService.getValidationMessage());
        }
    }
}
