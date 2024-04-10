package cl.technicaltest.user.controller;

import cl.technicaltest.user.dto.UserCreatedResponse;
import cl.technicaltest.user.dto.UserRequest;
import cl.technicaltest.user.exception.UserExistException;
import cl.technicaltest.user.mapper.UserMapper;
import cl.technicaltest.user.model.User;
import cl.technicaltest.user.service.UserService;
import cl.technicaltest.user.validator.UserRequestValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("user")
@Tag(name = "Users API")
public class UserController {

    private final UserService userService;
    private final UserRequestValidator userRequestValidator;

    @Autowired
    public UserController(UserService userService, UserRequestValidator userRequestValidator) {
        this.userService = userService;
        this.userRequestValidator = userRequestValidator;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user by its id")
    public User get(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping
    @Operation(summary = "Get all users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    @Operation(summary = "Create an user")
    public ResponseEntity<UserCreatedResponse> saveUser(@RequestBody @Valid UserRequest userRequest, BindingResult bindingResult) throws UserExistException {
        userRequestValidator.validateCreateUserRequest(bindingResult, userRequest);
        UserCreatedResponse response = UserMapper.userToUserCreatedResponse(userService.saveUser(userRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an user")
    public ResponseEntity<User> deleteUser(@PathVariable String id) throws UserExistException {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.deleteUser(id));
    }
}
