package cl.sentra.user.controller;

import cl.sentra.user.dto.UserErrorResponse;
import cl.sentra.user.dto.UserRequest;
import cl.sentra.user.exception.BadRequestException;
import cl.sentra.user.exception.NotFoundException;
import cl.sentra.user.exception.UserExistException;
import cl.sentra.user.model.User;
import cl.sentra.user.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
@Api(tags = "User Controller", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get User", notes = "Get User by id", response = User.class)
    public User get(@PathVariable String id) {
        Optional<User> optionalUser = userService.getUserById(id);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new NotFoundException(String.format("User with id %s not found", id));
    }

    @GetMapping
    @ApiOperation(value = "Get Users", notes = "Get all Users", response = ArrayList.class)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    @ApiOperation(value = "Create User", notes = "Create user validating password and email", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success", response = User.class),
            @ApiResponse(code = 400, message = "Bad request", response = UserErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = UserErrorResponse.class)
    })
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserRequest userRequest, BindingResult bindingResult) throws UserExistException {
        if(bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));
            throw new BadRequestException(errorMessage);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userRequest));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete User", notes = "Delete User by id", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = User.class),
            @ApiResponse(code = 404, message = "Bad request", response = UserErrorResponse.class),
            @ApiResponse(code = 500, message = "Internal server error", response = UserErrorResponse.class)
    })
    public ResponseEntity<User> deleteUser(@PathVariable String id) throws UserExistException {
        Optional<User> user = this.userService.getUserById(id);
        if(user.isPresent()) {
            this.userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body(user.get());
        }
        throw new NotFoundException(String.format("User with id %s not found", id));
    }
}
