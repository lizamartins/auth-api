package com.security.auth.controllers;

import com.security.auth.dtos.LoginRecordDTO;
import com.security.auth.dtos.RegisterRecordDTO;
import com.security.auth.dtos.TokenRecordDTO;
import com.security.auth.services.UserService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return ResponseEntity.status(HttpStatus.OK).body(new TokenRecordDTO(userService.login(loginDTO)));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRecordDTO registerDTO) {
        userService.register(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered sucessfully");
    }
}
