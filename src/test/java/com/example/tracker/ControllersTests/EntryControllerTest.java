package com.example.tracker.ControllersTests;

import com.example.tracker.dtos.EntryDto;
import com.example.tracker.models.Entry;
import com.example.tracker.models.WalletLocationsEnum;
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
public class EntryControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void createEntryExpectStatusOkGetExpectStatusOkDeleteExpectStatusOKAndGetExpectServerErrorTest() {
        EntryDto entry = new EntryDto();
        entry.setAmount(15);
        entry.setCurrencyName("Bitcoin");
        entry.setWalletLocation(WalletLocationsEnum.DESKTOP);


        FluxExchangeResult<EntryDto> testEntryFluxExchangeResult = this.webTestClient
                .post()
                .uri("api/entry")
                .body(Mono.just(entry), Entry.class)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(EntryDto.class);

        EntryDto testEntry = testEntryFluxExchangeResult.getResponseBody().blockLast();
        System.out.println(testEntry);
        assert testEntry != null;
        Long reference = testEntry.getReference();

        this.webTestClient
                .get()
                .uri("api/entry/{reference}", reference)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.reference").isNotEmpty()
                .jsonPath("$.currencyName").isEqualTo("Bitcoin")
                .jsonPath("$.amount").isEqualTo(15)
                .jsonPath("$.creationDateTime").isNotEmpty()
                .jsonPath("$.walletLocation").isEqualTo("DESKTOP")
                .jsonPath("$.currentEurosMarketValue").isNotEmpty();

        this.webTestClient
                .delete()
                .uri("api/entry/{reference}", reference)
                .exchange()
                .expectStatus()
                .isOk();

        this.webTestClient
                .get()
                .uri("api/entry/{reference}", reference)
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

    @Test
    public void updateCurrencyExpectStatusOKTest() {
        EntryDto entry = new EntryDto();
        entry.setAmount(15);
        entry.setCurrencyName("Bitcoin");
        entry.setWalletLocation(WalletLocationsEnum.DESKTOP);

        FluxExchangeResult<EntryDto> testEntryFluxExchangeResult = this.webTestClient
                .post()
                .uri("api/entry")
                .body(Mono.just(entry), Entry.class)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(EntryDto.class);

        EntryDto testEntry = testEntryFluxExchangeResult.getResponseBody().blockLast();
        assert testEntry != null;
        Long reference = testEntry.getReference();

        this.webTestClient
                .get()
                .uri("api/entry/{reference}", reference)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.reference").isNotEmpty()
                .jsonPath("$.currencyName").isEqualTo("Bitcoin")
                .jsonPath("$.amount").isEqualTo(15)
                .jsonPath("$.creationDateTime").isNotEmpty()
                .jsonPath("$.walletLocation").isEqualTo("DESKTOP")
                .jsonPath("$.currentEurosMarketValue").isNotEmpty();

        entry.setAmount(20);
        entry.setCurrencyName("Ethereum");
        entry.setWalletLocation(WalletLocationsEnum.PAPER);

        this.webTestClient
                .put()
                .uri("api/entry/{reference}", reference)
                .body(Mono.just(entry), Entry.class)
                .exchange()
                .expectStatus()
                .isOk();

        this.webTestClient
                .get()
                .uri("api/entry/{reference}", reference)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.reference").isNotEmpty()
                .jsonPath("$.currencyName").isEqualTo("Ethereum")
                .jsonPath("$.amount").isEqualTo(20)
                .jsonPath("$.creationDateTime").isNotEmpty()
                .jsonPath("$.walletLocation").isEqualTo("PAPER")
                .jsonPath("$.currentEurosMarketValue").isNotEmpty();


        this.webTestClient
                .delete()
                .uri("api/entry/{reference}", reference)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void getAllEntriesExpectStatusOKTest() {
        this.webTestClient
                .get()
                .uri("api/entry")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void getCurrentPortfolioValueExpectStatusOKTest() {
        this.webTestClient
                .get()
                .uri("api/entry/combined")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void createIncompleteEntryExpectStatusServerErrorTest() {
        EntryDto entry = new EntryDto();
        entry.setCurrencyName("Bitcoin");
        entry.setWalletLocation(WalletLocationsEnum.DESKTOP);

        this.webTestClient
                .post()
                .uri("api/entry")
                .body(Mono.just(entry), Entry.class)
                .exchange()
                .expectStatus()
                .is5xxServerError();

        EntryDto entryTwo = new EntryDto();
        entry.setAmount(15);
        entryTwo.setWalletLocation(WalletLocationsEnum.DESKTOP);

        this.webTestClient
                .post()
                .uri("api/entry")
                .body(Mono.just(entryTwo), Entry.class)
                .exchange()
                .expectStatus()
                .is5xxServerError();

        EntryDto entryThree = new EntryDto();
        entry.setAmount(15);
        entryThree.setCurrencyName("Bitcoin");

        this.webTestClient
                .post()
                .uri("api/entry")
                .body(Mono.just(entryThree), Entry.class)
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }
    @Test
    public void getEntryByWalletLocationExpectStatusOKTest() {
        EntryDto entry = new EntryDto();
        entry.setAmount(15);
        entry.setCurrencyName("Bitcoin");
        entry.setWalletLocation(WalletLocationsEnum.DESKTOP);


        FluxExchangeResult<EntryDto> testEntryFluxExchangeResult = this.webTestClient
                .post()
                .uri("api/entry")
                .body(Mono.just(entry), Entry.class)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(EntryDto.class);

        EntryDto testEntry = testEntryFluxExchangeResult.getResponseBody().blockLast();
        assert testEntry != null;
        Long reference = testEntry.getReference();
        WalletLocationsEnum walletLocation = testEntry.getWalletLocation();

        this.webTestClient
                .get()
                .uri("api/entry/wallet/{walletLocation}", walletLocation)
                .exchange()
                .expectStatus()
                .isOk();

        this.webTestClient
                .delete()
                .uri("api/entry/{reference}", reference)
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    public void getEntryByCurrencyExpectStatusOKTest() {
        EntryDto entry = new EntryDto();
        entry.setAmount(15);
        entry.setCurrencyName("Bitcoin");
        entry.setWalletLocation(WalletLocationsEnum.DESKTOP);


        FluxExchangeResult<EntryDto> testEntryFluxExchangeResult = this.webTestClient
                .post()
                .uri("api/entry")
                .body(Mono.just(entry), Entry.class)
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(EntryDto.class);

        EntryDto testEntry = testEntryFluxExchangeResult.getResponseBody().blockLast();
        assert testEntry != null;
        System.out.println(testEntry);
        Long reference = testEntry.getReference();
        String currencyName = testEntry.getCurrencyName();

        this.webTestClient
                .get()
                .uri("api/entry/currency/{currencyName}", currencyName)
                .exchange()
                .expectStatus()
                .isOk();

        this.webTestClient
                .delete()
                .uri("api/entry/{reference}", reference)
                .exchange()
                .expectStatus()
                .isOk();
    }
}
