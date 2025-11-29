package com.fin.Traxpenses.services;

import com.fin.Traxpenses.dto.UserCreateDTO;
import com.fin.Traxpenses.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    public String login(String number, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(number, password);
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        return jwtUtil.generateToken(number);
    }

    public void signup(UserCreateDTO userCreateDTO) {
        userService.createUser(userCreateDTO);
    }
}
