package com.example.veiculos.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MultaRequest {

    @NotEmpty
    @Size(min = 2, max = 100)
    private String local;

    @NotEmpty
    private String motivo;

    @NotNull
    private Float valor;

}
