package com.test.marketdata.producer.producer;

import com.test.marketdata.model.MarketData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class MarketDataProducer {
    @Value("${spring.kafka.topic.name}")
    private String topicName;

    private KafkaTemplate<String, MarketData> kafkaTemplate;

    public MarketDataProducer(KafkaTemplate<String, MarketData> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(MarketData marketData){
        Message<MarketData> marketDataMessage = MessageBuilder
                .withPayload(marketData)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();
        kafkaTemplate.send(marketDataMessage);
    }
}
