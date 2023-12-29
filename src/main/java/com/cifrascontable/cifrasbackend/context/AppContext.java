package com.cifrascontable.cifrasbackend.context;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {Constants.BASE_PACKAGE})
public class AppContext {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}