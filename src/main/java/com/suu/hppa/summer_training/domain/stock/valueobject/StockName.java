package com.suu.hppa.summer_training.domain.stock.valueobject;

public record StockName(String value) {
    public StockName(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Name must not be null");
        }

        String edgeBlankStripped = value.replaceAll("^[\\s\u3000]+|[\\s\u3000]+$", "");
        if (edgeBlankStripped.isEmpty()) {
            throw new IllegalArgumentException("Name must not be blank.");
        }

        String allowedPattern = "[A-Za-z0-9.()\\s\\u3000]+";
        if (!edgeBlankStripped.matches(allowedPattern)) {
            String invalidChars = edgeBlankStripped.replaceAll(allowedPattern, "");
            throw new IllegalArgumentException("Name contains invalid characters: " + invalidChars);
        }

        this.value = edgeBlankStripped;
    }
}
