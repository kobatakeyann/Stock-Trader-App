package com.suu.hppa.stocktradarapp.domain.stock.valueobject;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import com.suu.hppa.stocktradarapp.domain.stock.exception.StockValidationException;

public record Ticker(String value) {
    private static final Set<Character> ALLOWED_LETTERS = new LinkedHashSet<>(
            List.of(
                    'A', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
                    'N', 'P', 'R', 'S', 'T', 'U', 'W', 'X', 'Y'
            )
    );

    public Ticker(String value) {
        validate(value);
        this.value = value;
    }

    public static void validate(String value) {
        if (value == null) {
            throw new StockValidationException("Ticker must not be null.");
        }
        if (value.length() != 4) {
            throw new StockValidationException("Ticker must be 4 characters: " + value);
        }

        if (!Character.isDigit(value.charAt(0))) {
            throw new StockValidationException("1st character must be a digit: " + value);
        }
        if (!Character.isDigit(value.charAt(2))) {
            throw new StockValidationException("3rd character must be a digit: " + value);
        }

        validateAlphanumeric(value, 1);
        validateAlphanumeric(value, 3);
    }

    private static void validateAlphanumeric(String raw, int idx) {
        char c = raw.charAt(idx);
        if (Character.isDigit(c)) return;
        if (!Character.isUpperCase(c)) {
            throw new StockValidationException("Alphabet must be uppercase letter: " + raw);
        }
        if (!ALLOWED_LETTERS.contains(c)) {
            throw new StockValidationException(
                    String.format("Invalid character at pos %d: %c", (idx + 1), c)
                            + "\n Alphabet must be one of " + ALLOWED_LETTERS + ": " + raw
            );
        }
    }
}
