package com.houseofo;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HouseOfOConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}


