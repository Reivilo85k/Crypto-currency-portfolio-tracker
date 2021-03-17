package com.example.tracker.ControllersTests;


import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;


public class TickerServiceTest {

    @Test
    void getCurrencyValueStatusOKReturnsResponseTest() {
        WebTestClient
                .bindToServer()
                .baseUrl("https://api-pub.bitfinex.com/v2/")
                .build().get().uri("/ticker/tBTCEUR")
                .exchange()
                .expectStatus().isOk()
                .expectBody().returnResult();
    }
}
