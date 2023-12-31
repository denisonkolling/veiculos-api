package com.example.veiculos.repository;

import com.example.veiculos.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, String> {
    Veiculo findByPlaca(String placa);
}
