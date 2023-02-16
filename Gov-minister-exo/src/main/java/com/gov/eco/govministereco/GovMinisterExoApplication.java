package com.gov.eco.govministereco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(
        basePackages = "com.gov.clients"
)
public class GovMinisterExoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GovMinisterExoApplication.class, args);
    }

}
