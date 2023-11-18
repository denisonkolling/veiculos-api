package com.example.veiculos.service;

import com.example.veiculos.model.Multa;
import com.example.veiculos.model.Veiculo;
import com.example.veiculos.repository.MultaRepository;
import com.example.veiculos.repository.VeiculosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculosService {

    @Autowired
    private VeiculosRepository veiculosRepository;

    @Autowired
    private MultaRepository multaRepository;

    public List<Veiculo> listarTodosVeiculos() {
        return veiculosRepository.findAll();
    }

    public Veiculo consultarVeiculo(String placa) {
        return  veiculosRepository.findById(placa).orElseThrow(() -> new IllegalArgumentException("Placa não encontrada"));
    }

    public Veiculo cadastrarVeiculo(Veiculo veiculo) {
        boolean existe = veiculosRepository.existsById(veiculo.getPlaca());
        if (existe)
            throw new IllegalArgumentException("Placa não encontrada");
        veiculo = veiculosRepository.save(veiculo);
        return veiculo;
    }

    public Multa cadastrarMulta(String placa, Multa multa) {
        var veiculo = this.consultarVeiculo(placa);
        multa.setVeiculo(veiculo);
        multa = multaRepository.save(multa);
        return multa;
    }

}
