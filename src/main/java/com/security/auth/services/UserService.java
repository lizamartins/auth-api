package com.security.auth.services;

import com.security.auth.dtos.RegisterRecordDTO;
import com.security.auth.exceptions.UserAlreadyExistsException;
import com.security.auth.models.UserModel;
import com.security.auth.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void register(RegisterRecordDTO registerDTO) {
        if (this.userRepository.findByUsername(registerDTO.username()) != null)
            throw new UserAlreadyExistsException();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        UserModel newUser = new UserModel(registerDTO.username(), encryptedPassword, registerDTO.role());
        userRepository.save(newUser);
    }
}
