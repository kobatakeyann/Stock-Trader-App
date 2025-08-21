package com.suu.hppa.summer_training.domain.stock;

public record SharesIssued(long value) {

    public SharesIssued {
        if (value < 1 || value > 999_999_999_999L) {
            throw new IllegalArgumentException(
                    "SharesIssued must be between 1 and 999,999,999,999."
            );
        }
    }
}
