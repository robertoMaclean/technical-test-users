package cl.technicaltest.password.controller;

import cl.technicaltest.password.dto.PasswordCreateRequest;
import cl.technicaltest.password.model.Password;
import cl.technicaltest.password.service.PasswordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("password")
@Tag(name = "Password Validator Configuration API")
public class PasswordController {

    private final PasswordService passwordService;

    @Autowired
    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @PostMapping
    @Operation(summary = "Create the password validator")
    public ResponseEntity<Password> savePassword(@RequestBody @Valid PasswordCreateRequest passwordRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.passwordService.createPassword(passwordRequest)
        );
    }
}
