package com.example.veiculos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MULTAS")
public class Multa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String local;

    private String motivo;

    private Float valor;

    @ManyToOne
    @JoinColumn(name = "PLACA", referencedColumnName = "PLACA")
    private Veiculo veiculo;

}
