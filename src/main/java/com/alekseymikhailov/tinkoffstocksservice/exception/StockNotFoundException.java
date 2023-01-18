package com.alekseymikhailov.tinkoffstocksservice.exception;

public class StockNotFoundException extends RuntimeException{
    public StockNotFoundException(String message){
        super(message);
    }
}
