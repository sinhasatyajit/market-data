package com.test.marketdata.utility;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Utility {
    public static int getRandomNumber(int from,int to){
        return ThreadLocalRandom.current().nextInt(from, to) + from;
    }

    public static BigDecimal getRandomPrice(int precision) {
        Random r = new Random();
        return new BigDecimal(
                new Double(
                        r.nextInt(100))+r.nextDouble())
                .setScale(2, RoundingMode.CEILING);
    }
}
