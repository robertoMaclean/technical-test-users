package cl.technicaltest.user.controller;

import cl.technicaltest.password.service.PasswordService;
import cl.technicaltest.user.dto.UserCreatedResponse;
import cl.technicaltest.user.dto.UserRequest;
import cl.technicaltest.user.exception.BadRequestException;
import cl.technicaltest.user.exception.NotFoundException;
import cl.technicaltest.user.exception.UserExistException;
import cl.technicaltest.user.mapper.UserMapper;
import cl.technicaltest.user.model.User;
import cl.technicaltest.user.service.UserService;
import cl.technicaltest.user.validator.PasswordValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
@Tag(name = "Users API")
public class UserController {

    private final UserService userService;
    private final PasswordService passwordService;

    @Autowired
    public UserController(UserService userService, PasswordService passwordService) {
        this.userService = userService;
        this.passwordService = passwordService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user by its id")
    public User get(@PathVariable String id) {
        Optional<User> optionalUser = userService.getUserById(id);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new NotFoundException(String.format("User with id %s not found", id));
    }

    @GetMapping
    @Operation(summary = "Get all users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    @Operation(summary = "Create an user")
    public ResponseEntity<UserCreatedResponse> saveUser(@RequestBody @Valid UserRequest userRequest, BindingResult bindingResult) throws UserExistException {
        if(bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));
            throw new BadRequestException(errorMessage);
        }
        PasswordValidator passwordValidator = new PasswordValidator();
        if(!passwordValidator.isValid(userRequest.password(), passwordService.getRegex())) {
            throw new BadRequestException("Invalid password format: " + passwordService.getValidationMessage());
        }
        UserCreatedResponse response = UserMapper.userToUserCreatedResponse(userService.saveUser(userRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an user")
    public ResponseEntity<User> deleteUser(@PathVariable String id) throws UserExistException {
        Optional<User> user = this.userService.getUserById(id);
        if(user.isPresent()) {
            this.userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body(user.get());
        }
        throw new NotFoundException(String.format("User with id %s not found", id));
    }
}
