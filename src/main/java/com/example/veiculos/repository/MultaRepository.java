package com.example.veiculos.repository;

import com.example.veiculos.model.Multa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultaRepository extends JpaRepository<Multa, Integer> {
}
