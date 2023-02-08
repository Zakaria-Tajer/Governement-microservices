package com.gov.eco.govministereco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class GovMinisterExoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GovMinisterExoApplication.class, args);
    }

}
