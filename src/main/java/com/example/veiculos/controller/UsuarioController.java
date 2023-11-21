package com.example.veiculos.controller;

import com.example.veiculos.dto.UsuarioRequest;
import com.example.veiculos.dto.UsuarioResponse;
import com.example.veiculos.model.Usuario;
import com.example.veiculos.service.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ModelMapper mapper;


    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarUsuarios() {
        var usuarios = usuarioService.listarUsuarios();
        var response = usuarios.stream().map(u -> mapper.map(u, UsuarioResponse.class)).toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<UsuarioResponse> criarUsuario(@RequestBody @Valid UsuarioRequest request) {
        var usuario = mapper.map(request, Usuario.class);
        usuario = usuarioService.criarUsuario(usuario);
        var response = mapper.map(usuario, UsuarioResponse.class);
        return ResponseEntity.created(URI.create(usuario.getId().toString())).body(response);
    }

}
