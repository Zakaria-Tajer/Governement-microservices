package com.gov.justice.govministerjustice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients(
        basePackages = "com.gov.clients"
)
@SpringBootApplication
public class GovMinisterJusticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GovMinisterJusticeApplication.class, args);
    }

}
