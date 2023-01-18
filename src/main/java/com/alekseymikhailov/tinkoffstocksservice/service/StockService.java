package com.alekseymikhailov.tinkoffstocksservice.service;

import com.alekseymikhailov.tinkoffstocksservice.dto.FigiesDto;
import com.alekseymikhailov.tinkoffstocksservice.dto.StockPricesDto;
import com.alekseymikhailov.tinkoffstocksservice.dto.StocksDto;
import com.alekseymikhailov.tinkoffstocksservice.dto.TickersDto;
import com.alekseymikhailov.tinkoffstocksservice.model.Stock;

public interface StockService {
    Stock getStockByTicker(String ticker);
    StocksDto getStocksByTickers(TickersDto tickers);
    StockPricesDto getPrices(FigiesDto figiesDto);
}
