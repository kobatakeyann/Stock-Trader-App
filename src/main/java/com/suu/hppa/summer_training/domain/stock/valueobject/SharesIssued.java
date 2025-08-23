package com.suu.hppa.summer_training.domain.stock.valueobject;

public record SharesIssued(long value) {

    public SharesIssued {
        if (value < 1 || value > 999_999_999_999L) {
            throw new IllegalArgumentException(
                    "SharesIssued must be between 1 and 999,999,999,999."
            );
        }
    }

    public static SharesIssued fromStr(String value) {
        if (value == null) {
            throw new IllegalArgumentException("value of shares issued is null.");
        }
        try {
            return new SharesIssued(Long.parseLong(value));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("SharesIssued must be number: " + value, e);
        }
    }
}
