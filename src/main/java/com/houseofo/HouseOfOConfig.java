package com.houseofo;

import com.houseofo.data.dtos.UserDto;
import com.houseofo.data.model.User;
import com.houseofo.util.UserCascadeSaveMongoEventListener;
import org.apache.catalina.UserDatabase;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class HouseOfOConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setSkipNullEnabled(true);
        mapper.getConfiguration().setAmbiguityIgnored(true);
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper;
    }

    @Bean
    public UserCascadeSaveMongoEventListener userCascadingMongoEventListener() {
        return new UserCascadeSaveMongoEventListener();
    }




}


