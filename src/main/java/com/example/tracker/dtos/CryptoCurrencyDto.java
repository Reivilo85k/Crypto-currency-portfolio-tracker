package com.example.tracker.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CryptoCurrencyDto {
    private Long Id;
    private String currencyCode;
    private String currencyName;
}
