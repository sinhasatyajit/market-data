package com.test.marketdata.model;

import com.test.marketdata.utility.Utility;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class MarketData {

    private String ricCode;
    private BigDecimal price;
    private LocalDateTime lastUpdatedTime;

    public List<String> populateRicCodes(){
        List<String> riccodes = new ArrayList<>();
            for (int i = 65; i <= 90; i++) {
                char c = (char) i;
                riccodes.add(c + ".HK");
            }
            return riccodes;
    }

    public Map<String, MarketData> populateData(){
        return populateRicCodes().stream().map(ricCode ->
                        new MarketData(ricCode,Utility.getRandomPrice(2),LocalDateTime.now()))
                .collect(Collectors.toMap(data -> data.getRicCode(), Function.identity()));
    }



}
