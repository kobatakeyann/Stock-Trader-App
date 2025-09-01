package com.suu.hppa.stocktradarapp.domain.stock.valueobject;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import com.suu.hppa.stocktradarapp.domain.stock.exception.StockValidationException;

public record StockName(String value) {
    private static final Pattern ALLOWED_PATTERN = Pattern.compile("[A-Za-z0-9.()\\s\\u3000]+");

    public StockName(String value) {
        String validated = validate(value);
        this.value = validated;
    }

    public static String validate(String value) {
        if (value == null) {
            throw new StockValidationException("Name must not be null.");
        }

        // strip both half-width and full-width spaces from the edges
        String edgeBlankStripped = value.replaceAll("^[\\s\u3000]+|[\\s\u3000]+$", "");
        if (edgeBlankStripped.isEmpty()) {
            throw new StockValidationException("Name must not be blank.");
        }

        Set<Character> invalidChars = edgeBlankStripped.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> !ALLOWED_PATTERN.matcher(String.valueOf(c)).matches())
                .collect(Collectors.toSet());

        if (!invalidChars.isEmpty()) {
            throw new StockValidationException("Name contains invalid characters: " + invalidChars);
        }
        return edgeBlankStripped;
    }
}
