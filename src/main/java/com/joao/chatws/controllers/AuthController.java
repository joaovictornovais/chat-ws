package com.joao.chatws.controllers;

import com.joao.chatws.domain.user.LoginRequestDTO;
import com.joao.chatws.domain.user.RegisterRequestDTO;
import com.joao.chatws.domain.user.TokenResponseDTO;
import com.joao.chatws.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<TokenResponseDTO> register(@RequestBody RegisterRequestDTO data) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.register(data));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginRequestDTO data) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(data));
    }

}
