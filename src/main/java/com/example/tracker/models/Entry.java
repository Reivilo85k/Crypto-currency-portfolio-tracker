package com.example.tracker.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Entry {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reference;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private CryptoCurrency currency;

    private Integer amount;

    private Instant creationDateTime;

    @Enumerated(EnumType.STRING)
    private WalletLocationsEnum walletLocation;

    private Float currentEurosMarketValue;
}
