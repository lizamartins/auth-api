package com.security.auth.controllers;

import com.security.auth.dtos.LoginRecordDTO;
import com.security.auth.dtos.RegisterRecordDTO;
import com.security.auth.exceptions.UserNotFoundException;
import com.security.auth.repositories.UserRepository;
import com.security.auth.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRecordDTO loginDTO) {
        userService.login(loginDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Login finish");
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRecordDTO registerDTO) {
        userService.register(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered sucessfully");
    }
}
