package com.gov.clients.ecoMinister;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class FooConfiguration {
    @Bean
    Logger feignLoggerLevel() {
        return Logger.getGlobal();
    }
}