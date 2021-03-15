package com.example.tracker.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TickerResponse {

    private Float bid;
    private Float bidSize;
    private Float ask;
    private Float askSize;
    private Float dailyChange;
    private Float dailyChangeRelative;
    private Float lastPrice;
    private Float volume;
    private Float high;
    private Float low;

}
