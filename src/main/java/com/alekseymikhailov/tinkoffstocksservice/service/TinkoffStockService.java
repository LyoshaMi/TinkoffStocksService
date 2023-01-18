package com.alekseymikhailov.tinkoffstocksservice.service;

import com.alekseymikhailov.tinkoffstocksservice.dto.StocksDto;
import com.alekseymikhailov.tinkoffstocksservice.dto.TickersDto;
import com.alekseymikhailov.tinkoffstocksservice.exception.StockNotFoundException;
import com.alekseymikhailov.tinkoffstocksservice.model.Currency;
import com.alekseymikhailov.tinkoffstocksservice.model.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.tinkoff.invest.openapi.OpenApi;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrumentList;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TinkoffStockService implements StockService{

    private final OpenApi openApi;

    @Async
    public CompletableFuture<MarketInstrumentList> getMarketInstrumentTicker(String ticker){
        var context = openApi.getMarketContext();
        return context.searchMarketInstrumentsByTicker(ticker);
    }

    @Override
    public Stock getStockByTicker(String ticker) {
        var cf = getMarketInstrumentTicker(ticker);
        var list = cf.join().getInstruments();
        if (list.isEmpty()){
            throw new StockNotFoundException(String.format("Stock %S not found", ticker));
        }

        var item = list.get(0);
        return new Stock(
                item.getTicker(),
                item.getFigi(),
                item.getName(),
                item.getType().getValue(),
                Currency.valueOf(item.getCurrency().getValue()),
                "TINKOFF");
    }

    @Override
    public StocksDto getStocksByTickers(TickersDto tickers){
        List<CompletableFuture<MarketInstrumentList>> marketInstrument = new ArrayList<>();
        tickers.getTickers().forEach(ticker -> marketInstrument.add(getMarketInstrumentTicker(ticker)));
        List<Stock> stocks = marketInstrument.stream()
                .map(CompletableFuture::join)
                .map(mi -> {
                    if (!mi.getInstruments().isEmpty()){
                        return mi.getInstruments().get(0);
                    }
                    return null;
                })
                .filter(al -> Objects.nonNull(al))
                .map(mi -> new Stock(
                        mi.getTicker(),
                        mi.getFigi(),
                        mi.getName(),
                        mi.getType().getValue(),
                        Currency.valueOf(mi.getCurrency().getValue()),
                        "TINKOFF"
                ))
                .collect(Collectors.toList());

        return new StocksDto(stocks);
    }
}
