package com.suu.hppa.summer_training.domain.stock.exception;

public class StockValidationException extends Exception {
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
