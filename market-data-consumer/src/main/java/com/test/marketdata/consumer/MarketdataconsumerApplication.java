package com.test.marketdata.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MarketdataconsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MarketdataconsumerApplication.class);

    }
}
