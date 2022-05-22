package com.algaworks.algalog.common;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    //Configurando o model mapper para que o spring consiga enxerga-lo
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
