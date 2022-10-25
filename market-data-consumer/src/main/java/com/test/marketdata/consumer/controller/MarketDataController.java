package com.test.marketdata.consumer.controller;

import com.test.marketdata.consumer.service.MarketDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
public class MarketDataController {

    private MarketDataService marketDataService;

    public MarketDataController(MarketDataService marketDataService) {
        this.marketDataService = marketDataService;
    }

    @GetMapping
    public ResponseEntity<Map<String, BigDecimal>> gePrice(){
        return new ResponseEntity<>(marketDataService.getPrice(),HttpStatus.OK) ;
    }
}
