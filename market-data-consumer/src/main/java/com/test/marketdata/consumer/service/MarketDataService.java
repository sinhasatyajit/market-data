package com.test.marketdata.consumer.service;

import com.test.marketdata.consumer.consumer.MarketDataConsumer;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

@Service
public class MarketDataService {
    private MarketDataConsumer marketDataConsumer;

    public MarketDataService(MarketDataConsumer marketDataConsumer) {
        this.marketDataConsumer = marketDataConsumer;
    }

    public Map<String, BigDecimal> getPrice(){
        return marketDataConsumer.getPrice();
    }
}
