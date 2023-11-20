package com.example.veiculos.utils;

import com.example.veiculos.model.Multa;
import com.example.veiculos.model.TipoVeiculo;
import com.example.veiculos.model.Usuario;
import com.example.veiculos.model.Veiculo;
import com.example.veiculos.repository.MultaRepository;
import com.example.veiculos.repository.UsuarioRepository;
import com.example.veiculos.repository.VeiculoRepository;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitialData {

    @Autowired
    MultaRepository multaRepository;

    @Autowired
    VeiculoRepository veiculoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

//    @PostConstruct
    public void criarUsuarios() {

        List<Usuario> listaUsuarios = new ArrayList<>();

        Usuario usuario1 = new Usuario();
        usuario1.setNome("Scooby Doo");
        usuario1.setEmail("scoobydoo@scoobydoo.com");
        usuario1.setSenha("123456");

        Usuario usuario2 = new Usuario();
        usuario2.setNome("Salsicha Rogers");
        usuario2.setEmail("salsicharogers@scoobydoo.com");
        usuario2.setSenha("123456");

        listaUsuarios.add(usuario1);
        listaUsuarios.add(usuario2);

        for (Usuario usuario : listaUsuarios) {
            Usuario salvarUsuario = usuarioRepository.save(usuario);
        }

    }

//    @PostConstruct
    public void criarVeiculo() {

        Veiculo veiculo1 = new Veiculo("SCB-1969", TipoVeiculo.AUTOMOVEL, "Dodge A100", 1969, "verde");
        Veiculo salvarVeiculo = veiculoRepository.save(veiculo1);

    }

    @PostConstruct
    public void  crirMultas() {

        List<Multa> listaMultas = new ArrayList<>();

        Multa multa1 = new Multa(veiculoRepository.findByPlaca("SCB-1969"), "Farol apagado", "Los Angeles", 250F);
        Multa multa2 = new Multa(veiculoRepository.findByPlaca("SCB-1969"), "Excesso velocidade", "Santa Mônica", 400F);

        listaMultas.add(multa1);
        listaMultas.add(multa2);

        for (Multa multa : listaMultas) {
            Multa salvarMultas = multaRepository.save(multa);
        }

    }
}
