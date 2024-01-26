package com.security.auth.controllers;

import com.security.auth.dtos.RegisterRecordDTO;
import com.security.auth.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRecordDTO registerDTO) {
        userService.register(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered sucessfully");
    }
}
