package com.example.tracker.services;

import com.example.tracker.exceptions.TrackerException;
import com.example.tracker.models.TickerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class TickerService {

    WebClient client = WebClient.create("https://api-pub.bitfinex.com/v2/");

    public Float getCurrencyValue(String currencyCode){
        Mono<Float[]> response =  client.get()
                .uri("/ticker"+ "/t" + currencyCode + "EUR")
                .retrieve()
                .onStatus(httpStatus -> !HttpStatus.OK.equals(httpStatus),
                        clientResponse ->
                                Mono.error(new TrackerException("Failed to connect to currency data provider")))
                .onStatus(httpStatus -> HttpStatus.BAD_REQUEST.equals(httpStatus),
                        clientResponse ->
                                Mono.error(new TrackerException("Failed to retrieve data from data provider with " +
                                        "this currency code")))
                .bodyToMono(Float[].class).log();

        Float[] result = response.block();
        assert result != null;
        TickerResponse tickerResponse = mapToTickerResponse(result);

        return tickerResponse.getBid();
    }

    public TickerResponse mapToTickerResponse(Float[] result){
        return TickerResponse.builder()
                .bid(result[0])
                .bidSize(result[1])
                .ask(result[2])
                .askSize(result[3])
                .dailyChange(result[4])
                .dailyChangeRelative(result[5])
                .lastPrice(result[6])
                .volume(result[7])
                .high(result[8])
                .low(result[9])
                .build();
    }
}
