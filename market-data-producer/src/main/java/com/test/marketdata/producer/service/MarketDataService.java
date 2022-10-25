package com.test.marketdata.producer.service;

import com.test.marketdata.model.MarketData;
import com.test.marketdata.producer.producer.MarketDataProducer;
import com.test.marketdata.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class MarketDataService {
    private static MarketData marketData;
    private static List<String> ricCodes;
    private static Map<String, MarketData> marketDataMap;

    @Autowired
    private MarketDataProducer marketDataProducer;

    public MarketDataService() {
        marketData = new MarketData();
        ricCodes = marketData.populateRicCodes();
        marketDataMap = marketData.populateData();
    }
    @Scheduled(fixedDelay = 10)
    public void publish(){
        int randomNumber = Utility.getRandomNumber(0, ricCodes.size());
        String randomRicCode = ricCodes.get(randomNumber);
        MarketData marketData = marketDataMap.get(randomRicCode);
        BigDecimal randomPrice = Utility.getRandomPrice(2);
        marketData.setPrice(randomPrice);
        marketData.setLastUpdatedTime(LocalDateTime.now());
        marketDataProducer.sendMessage(marketData);
    }
}
