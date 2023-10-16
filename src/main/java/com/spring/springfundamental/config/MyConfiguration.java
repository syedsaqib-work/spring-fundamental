package com.spring.springfundamental.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MyConfiguration {

    @Bean
    public Map<String, Integer> populateConfigMap(){
        Map<String, Integer> map = new HashMap<>();
        map.put("syed", 26);
        System.out.println("populateConfigMap ");
        return map;
    }
}
