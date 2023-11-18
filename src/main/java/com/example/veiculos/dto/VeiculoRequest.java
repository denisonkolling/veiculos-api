package com.example.veiculos.dto;

import com.example.veiculos.model.TipoVeiculo;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class VeiculoRequest {

    @NotEmpty
    private String placa;

    @NotNull
    private TipoVeiculo tipo;

    @NotEmpty
    private String nome;

    @NotNull
    @Positive
    private Integer anoFabricacao;

    @NotEmpty
    private String cor;
}
