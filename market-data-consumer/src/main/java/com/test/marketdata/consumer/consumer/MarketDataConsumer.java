package com.test.marketdata.consumer.consumer;

import com.test.marketdata.model.MarketData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class MarketDataConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MarketDataConsumer.class);
    private static Map<String,MarketData> marketDataMap;

    public MarketDataConsumer(){
        marketDataMap = new ConcurrentHashMap<>();
    }


    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void getPublishedData(MarketData marketData){
        LOGGER.info(marketData.toString());
        marketDataMap.put(marketData.getRicCode(),marketData);
    }

    public Map<String, BigDecimal> getPrice(){
        TreeMap<String, BigDecimal> sortedPriceMap = marketDataMap.values()
                        .parallelStream()
                        .collect(Collectors.toMap(data -> data.getRicCode(), data -> data.getPrice(),(o1, o2) -> o1, TreeMap::new));
        return Collections.unmodifiableSortedMap(sortedPriceMap);
    }
}
