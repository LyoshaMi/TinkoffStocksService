package com.alekseymikhailov.tinkoffstocksservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableConfigurationProperties
@EnableAsync
public class TinkoffStocksServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TinkoffStocksServiceApplication.class, args);
    }

}
