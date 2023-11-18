package com.example.veiculos;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VeiculosApplication {

    public static void main(String[] args) {
        SpringApplication.run(VeiculosApplication.class, args);
    }

    @Bean
    public ModelMapper obterModelMapper() {
        return new ModelMapper();
    }

}
