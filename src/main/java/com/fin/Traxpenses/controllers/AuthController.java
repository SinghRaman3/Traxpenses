package com.fin.Traxpenses.controllers;

import com.fin.Traxpenses.dto.UserCreateDTO;
import com.fin.Traxpenses.dto.UserLoginDTO;
import com.fin.Traxpenses.services.AuthService;
import com.fin.Traxpenses.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserCreateDTO userCreateDTO) {
        authService.signup(userCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO) {
        String number = userLoginDTO.getNumber(), password = userLoginDTO.getPassword();
        String jwtToken = authService.login(number, password);
        return new ResponseEntity<>(Map.of("token", jwtToken), HttpStatus.OK);
    }
}
