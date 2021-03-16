package com.example.tracker.models;

import lombok.*;

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
    @JoinColumn(name = "currency_name")
    @NonNull
    private CryptoCurrency currency;
    @NonNull
    private Integer amount;

    private Instant creationDateTime;

    @Enumerated(EnumType.STRING)
    @NonNull
    private WalletLocationsEnum walletLocation;

    private Float currentEurosMarketValue;
}
