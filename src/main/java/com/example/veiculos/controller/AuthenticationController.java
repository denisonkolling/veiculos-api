package com.example.veiculos.controller;

import com.example.veiculos.dto.AuthenticationRequest;
import com.example.veiculos.dto.AuthenticationResponse;
import com.example.veiculos.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest request) {
        var token = authenticationService.authentication(request.getEmail(), request.getSenha());
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

}