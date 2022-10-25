package com.test.marketdata.consumer.sseemitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class SseEmitterHelper {
    static Logger logger = LoggerFactory.getLogger(SseEmitterHelper.class);
    private static List<SseEmitter> emitterList = new CopyOnWriteArrayList<>();

    public static SseEmitter subscribe(){
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        try {
            sseEmitter.send(SseEmitter.event().name("price"));
            logger.info("successfully subscribed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sseEmitter.onCompletion(()->emitterList.remove(sseEmitter));
        emitterList.add(sseEmitter);
        return sseEmitter;
    }

    public static void dispatch(Map<String, BigDecimal> priceData){
        emitterList.stream().forEach(emitter->
        {
            try {
                emitter.send(SseEmitter.event().name("price-data").data(priceData));
                logger.info("published data : " + priceData);
            } catch (IOException e) {
                e.printStackTrace();
                emitterList.remove(emitter);
            }
        });
    }
}
