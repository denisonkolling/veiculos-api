package com.example.veiculos.controller;

import com.example.veiculos.dto.MultaRequest;
import com.example.veiculos.dto.MultaResponse;
import com.example.veiculos.dto.VeiculoRequest;
import com.example.veiculos.dto.VeiculoResponse;
import com.example.veiculos.model.Multa;
import com.example.veiculos.model.Veiculo;
import com.example.veiculos.service.VeiculosService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculosService veiculoService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    @RolesAllowed( {"ADMIN","USUARIO"} )
    public ResponseEntity<List<VeiculoResponse>> listarVeiculos() {
        var veiculos = veiculoService.listarTodosVeiculos();
        var response = new ArrayList<VeiculoResponse>();
        for (Veiculo veiculo : veiculos) {
            var veiculoDTO = mapper.map(veiculo, VeiculoResponse.class);
            if (veiculo.possuiMultas()) {
                var multasDTO = veiculo.getMultas().stream()
                        .map(m -> mapper.map(m, MultaResponse.class)).toList();
                veiculoDTO.setMultas(multasDTO);
            }
            response.add(veiculoDTO);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{placa}")
    @RolesAllowed( {"ADMIN","USUARIO"} )
    public ResponseEntity<VeiculoResponse> consultarVeiculo(@PathVariable String placa) {
        Veiculo veiculo = veiculoService.consultarVeiculo(placa);
        var response = mapper.map(veiculo, VeiculoResponse.class);
        if (veiculo.possuiMultas()) {
            var multasDTO = veiculo.getMultas().stream()
                    .map(m -> mapper.map(m, MultaResponse.class)).toList();
            response.setMultas(multasDTO);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<VeiculoResponse> cadastrarVeiculo(@RequestBody @Valid VeiculoRequest request) {
        var veiculo = mapper.map(request, Veiculo.class);
        veiculo = veiculoService.cadastrarVeiculo(veiculo);
        var response = mapper.map(veiculo, VeiculoResponse.class);
        return ResponseEntity.created(URI.create(veiculo.getPlaca())).body(response);
    }

    @PostMapping("/{placa}/multas")
    @RolesAllowed("ADMIN")
    public ResponseEntity<MultaResponse> cadastrarMulta(@PathVariable String placa, @RequestBody @Valid MultaRequest request) {
        var multa = mapper.map(request, Multa.class);
        multa = veiculoService.cadastrarMulta(placa, multa);
        var response = mapper.map(multa, MultaResponse.class);
        return ResponseEntity.ok(response);
    }

}
