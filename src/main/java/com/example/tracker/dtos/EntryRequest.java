package com.example.tracker.dtos;

import com.example.tracker.models.WalletLocationsEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntryRequest {
    private String currencyName;
    private Float amount;
    private WalletLocationsEnum walletLocation;
}
