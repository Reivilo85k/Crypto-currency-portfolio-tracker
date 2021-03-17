package com.example.tracker.services;

import com.example.tracker.dtos.CryptoCurrencyDto;
import com.example.tracker.models.CryptoCurrency;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@Log
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CryptoCurrencyControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void createCurrencyExpectStatusOkGetExpectStatusOkDeleteExpectStatusOKAndGetExpectServerErrorTest() {
        CryptoCurrencyDto cryptoCurrency = new CryptoCurrencyDto();
        cryptoCurrency.setCurrencyCode("AVAX");
        cryptoCurrency.setCurrencyName("Avalanche");

        FluxExchangeResult<CryptoCurrency> testCurrencyFluxExchangeResult = this.webTestClient
                .post()
                .uri("api/currency")
                .body(Mono.just(cryptoCurrency), CryptoCurrency.class)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(CryptoCurrency.class);

        String currencyName = "Avalanche";

        this.webTestClient
                .get()
                .uri("api/currency/{currencyName}", currencyName)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.currencyName").isEqualTo("Avalanche")
                .jsonPath("$.currencyCode").isEqualTo("AVAX");

        this.webTestClient
                .delete()
                .uri("api/currency/{currencyName}", currencyName)
                .exchange()
                .expectStatus()
                .isOk();

        this.webTestClient
                .get()
                .uri("api/currency/{currencyName}", currencyName)
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

    @Test
    public void updateCurrencyExpectStatusServerErrorTest() {
        //Since currencyName is also the id it should not be possible to update it
        CryptoCurrencyDto cryptoCurrency = new CryptoCurrencyDto();
        cryptoCurrency.setCurrencyCode("AVAX");
        cryptoCurrency.setCurrencyName("Avalanche");

        FluxExchangeResult<CryptoCurrency> testCurrencyFluxExchangeResult = this.webTestClient
                .post()
                .uri("api/currency")
                .body(Mono.just(cryptoCurrency), CryptoCurrency.class)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(CryptoCurrency.class);

        String currencyName = "Avalanche";

        this.webTestClient
                .get()
                .uri("api/currency/{currencyName}", currencyName)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody();

        cryptoCurrency.setCurrencyCode("ATOM");
        cryptoCurrency.setCurrencyName("Cosmos");

        this.webTestClient
                .put()
                .uri("api/currency/{currencyName}", currencyName)
                .body(Mono.just(cryptoCurrency), CryptoCurrency.class)
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

    @Test
    public void updateCurrencyExpectStatusOKTest() {
        //currencyCode is updatable
        CryptoCurrencyDto cryptoCurrency = new CryptoCurrencyDto();
        cryptoCurrency.setCurrencyCode("AVAX");
        cryptoCurrency.setCurrencyName("Avalanche");

        FluxExchangeResult<CryptoCurrency> testCurrencyFluxExchangeResult = this.webTestClient
                .post()
                .uri("api/currency")
                .body(Mono.just(cryptoCurrency), CryptoCurrency.class)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(CryptoCurrency.class);

        String currencyName = "Avalanche";

        this.webTestClient
                .get()
                .uri("api/currency/{currencyName}", currencyName)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody();

        cryptoCurrency.setCurrencyCode("ATOM");

        this.webTestClient
                .put()
                .uri("api/currency/{currencyName}", currencyName)
                .body(Mono.just(cryptoCurrency), CryptoCurrency.class)
                .exchange()
                .expectStatus()
                .isOk();

        this.webTestClient
                .get()
                .uri("api/currency/{currencyName}", currencyName)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.currencyName").isEqualTo("Avalanche")
                .jsonPath("$.currencyCode").isEqualTo("ATOM");

        this.webTestClient
                .delete()
                .uri("api/currency/{currencyName}", currencyName)
                .exchange()
                .expectStatus()
                .isOk();

        this.webTestClient
                .get()
                .uri("api/currency/{currencyName}", currencyName)
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

    @Test
    public void getAllCurrenciesExpectStatusOK() {
        this.webTestClient
                .get()
                .uri("api/currency")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void createIncompleteCurrencyExpectStatusServerErrorTest() {
        CryptoCurrencyDto cryptoCurrency = new CryptoCurrencyDto();
        cryptoCurrency.setCurrencyName("Avalanche");

        this.webTestClient
                .post()
                .uri("api/currency")
                .body(Mono.just(cryptoCurrency), CryptoCurrency.class)
                .exchange()
                .expectStatus()
                .is5xxServerError();

        CryptoCurrencyDto cryptoCurrencyTwo = new CryptoCurrencyDto();
        cryptoCurrencyTwo.setCurrencyCode("AVAX");

        this.webTestClient
                .post()
                .uri("api/currency")
                .body(Mono.just(cryptoCurrencyTwo), CryptoCurrency.class)
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }
}

