package com.security.auth.services;

import com.security.auth.dtos.LoginRecordDTO;
import com.security.auth.dtos.RegisterRecordDTO;
import com.security.auth.exceptions.UserAlreadyExistsException;
import com.security.auth.exceptions.UserNotFoundException;
import com.security.auth.infra.TokenService;
import com.security.auth.models.UserModel;
import com.security.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    @Lazy
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    public String login(LoginRecordDTO loginDTO) {
        if (this.userRepository.findByLogin(loginDTO.username()) == null)
            throw new UserNotFoundException();

        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserModel) auth.getPrincipal());
        return token;
    }

    public void register(RegisterRecordDTO registerDTO) {
        if (this.userRepository.findByLogin(registerDTO.username()) != null)
            throw new UserAlreadyExistsException();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        UserModel newUser = new UserModel(registerDTO.username(), encryptedPassword, registerDTO.role());
        userRepository.save(newUser);
    }
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByLogin(login);
    }
}
