package com.example.tracker.dtos;

import com.example.tracker.models.WalletLocations;
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
    private Long userId;
    private String currencyName;
    private Integer amount;
    private Instant creationDateTime;
    private WalletLocations walletLocation;
    private Float currentEurosMarketValue;
}
