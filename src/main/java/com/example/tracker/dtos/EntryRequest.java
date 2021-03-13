package com.example.tracker.dtos;

import com.example.tracker.models.WalletLocations;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class EntryRequest {
    private String currencyName;
    private Float amount;
    private WalletLocations walletLocation;
}
