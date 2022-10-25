package com.test.marketdata.consumer.controller;

import com.test.marketdata.consumer.service.MarketDataService;
import com.test.marketdata.consumer.sseemitter.SseEmitterHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.math.BigDecimal;
import java.util.Map;
@RestController
@CrossOrigin
public class MarketDataController {
    private MarketDataService marketDataService;

    public MarketDataController(MarketDataService marketDataService) {
        this.marketDataService = marketDataService;
    }

    @GetMapping
    public ResponseEntity<Map<String, BigDecimal>> gePrice(){
        return new ResponseEntity<>(marketDataService.getPrice(),HttpStatus.OK) ;
    }


    //Server sent event endpoints
    @GetMapping(value = "/subscribe",consumes = MediaType.ALL_VALUE)
    public SseEmitter subscribe(){
        return SseEmitterHelper.subscribe();
    }
}
