package com.suu.hppa.stocktradarapp.domain.stock.exception;

public class StockValidationException extends IllegalArgumentException {
    public StockValidationException(String message) {
        super(message);
    }

    public StockValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public StockValidationException(Throwable cause) {
        super(cause);
    }
}
