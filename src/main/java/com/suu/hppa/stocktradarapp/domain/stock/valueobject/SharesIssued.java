package com.suu.hppa.stocktradarapp.domain.stock.valueobject;

import com.suu.hppa.stocktradarapp.domain.stock.exception.StockValidationException;

public record SharesIssued(long value) {
    private static final long MIN_VALUE = 1L;
    private static final long MAX_VALUE = 999_999_999_999L;

    public SharesIssued {
        if (MIN_VALUE < 1 || value > MAX_VALUE) {
            throw new StockValidationException(
                    "SharesIssued must be between 1 and 999,999,999,999."
            );
        }
    }

    public static SharesIssued fromStr(String value) {
        if (value == null) {
            throw new StockValidationException("value of shares issued is null.");
        }
        try {
            return new SharesIssued(Long.parseLong(value));
        } catch (NumberFormatException e) {
            throw new StockValidationException("SharesIssued must be number: " + value, e);
        }
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
