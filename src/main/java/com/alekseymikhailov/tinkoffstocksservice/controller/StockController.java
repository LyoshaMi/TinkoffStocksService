package com.alekseymikhailov.tinkoffstocksservice.controller;

import com.alekseymikhailov.tinkoffstocksservice.dto.FigiesDto;
import com.alekseymikhailov.tinkoffstocksservice.dto.StockPricesDto;
import com.alekseymikhailov.tinkoffstocksservice.dto.StocksDto;
import com.alekseymikhailov.tinkoffstocksservice.dto.TickersDto;
import com.alekseymikhailov.tinkoffstocksservice.model.Stock;
import com.alekseymikhailov.tinkoffstocksservice.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StockController {
    
    private final StockService stockService;

    @GetMapping("/stocks/{ticker}")
    public Stock getStock(@PathVariable String ticker){
        return stockService.getStockByTicker(ticker);
    }

    @PostMapping(value = "/stocks/getStocksByTickers")
    public StocksDto getStocksByTickers(@RequestBody TickersDto tickersDto){
        return stockService.getStocksByTickers(tickersDto);
    }

    @PostMapping("/prices")
    public StockPricesDto getPrices(@RequestBody FigiesDto figiesDto){
        return stockService.getPrices(figiesDto);
    }
}
