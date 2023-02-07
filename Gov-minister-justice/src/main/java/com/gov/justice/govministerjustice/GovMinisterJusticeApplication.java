package com.gov.justice.govministerjustice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class GovMinisterJusticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GovMinisterJusticeApplication.class, args);
    }

}
