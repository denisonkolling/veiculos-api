package com.example.veiculos.service;

import com.example.veiculos.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String authentication(String email, String password) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(email, password);
            Authentication authenticate = authenticationManager.authenticate(authToken);
            var user  = (Usuario) authenticate.getPrincipal();
            String token = tokenService.criarToken(user);
            return token;
        } catch (AuthenticationException e) {
            throw new IllegalArgumentException("Usuário ou Senha Inválidos");
        }
    }

}
