package com.alekseymikhailov.tinkoffstocksservice.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
public class StockPricesDto {
    private List<StockPrice> prices;
}
