package com.lc.apilc.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurations {

    @Value("${application.name}")
    private String appName;

    @Bean(name = "appName")
    public String applicationName(){
        return this.appName;
    }
}
