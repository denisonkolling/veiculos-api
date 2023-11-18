package com.example.veiculos.repository;

import com.example.veiculos.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculosRepository extends JpaRepository<Veiculo, String> {
}
