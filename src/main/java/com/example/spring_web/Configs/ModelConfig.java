package com.example.spring_web.Configs;

import org.h2.engine.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelConfig {
    @Bean
    public ModelMapper getMapper(){
        return new ModelMapper();
    }
}
