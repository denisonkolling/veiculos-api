package com.example.veiculos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "VEICULOS")
public class Veiculo {

    @Id
    private String placa;

    @Enumerated(EnumType.STRING)
    private String nome;

    private Integer anoFabricacao;

    private String cor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "veiculo")
    private List<Multa> multas;


}
