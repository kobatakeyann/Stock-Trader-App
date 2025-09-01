package com.suu.hppa.stocktradarapp.application.stock.service.usecase.register;

public class StockRegistrationException extends Exception {
    public StockRegistrationException(String message) {
        super(message);
    }

    public StockRegistrationException(String message, Throwable cause) {
        super(message, cause);
    }

    public StockRegistrationException(Throwable cause) {
        super(cause);
    }
}
