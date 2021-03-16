package com.example.tracker.services;

import com.example.tracker.dtos.EntryDto;
import com.example.tracker.models.CryptoCurrency;
import com.example.tracker.models.Entry;
import com.example.tracker.models.WalletLocationsEnum;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;


@Log
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EntryControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void createEntryTest() {
        EntryDto entry = new EntryDto();
        entry.setAmount(15);
        entry.setCurrencyName("Bitcoin");
        entry.setWalletLocation(WalletLocationsEnum.DESKTOP);


        this.webTestClient
                .post()
                .uri("api/entry")
                .body(Mono.just(entry), Entry.class)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(Void.class);
    }
}
