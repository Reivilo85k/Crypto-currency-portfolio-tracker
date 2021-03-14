package com.example.tracker.dtos;

import com.example.tracker.models.WalletLocationsEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntryDto {
    private Long reference;
    private Long currencyId;
    private Integer amount;
    private Instant creationDateTime;
    private WalletLocationsEnum walletLocation;
    private Float currentEurosMarketValue;
}
