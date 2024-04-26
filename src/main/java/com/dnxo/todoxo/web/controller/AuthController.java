package com.dnxo.todoxo.web.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnxo.todoxo.service.dto.auth.AuthLoginDto;
import com.dnxo.todoxo.web.config.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody AuthLoginDto authLoginDto) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(authLoginDto.getUsername(),
                authLoginDto.getPassword());
        Authentication auth = this.authenticationManager.authenticate(login);
        System.out.println(auth.isAuthenticated());
        System.out.println(auth.getPrincipal());

        String jwt = this.jwtUtil.create(authLoginDto.getUsername());

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
    }

}
