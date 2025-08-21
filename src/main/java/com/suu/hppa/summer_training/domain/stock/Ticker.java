package com.suu.hppa.summer_training.domain.stock;

import java.util.Set;

public record Ticker(String value) {
    private static final Set<Character> ALLOWED_LETTERS = Set.of(
            'A', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
            'N', 'P', 'R', 'S', 'T', 'U', 'W', 'X', 'Y'
    );

    public Ticker(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Ticker must not be null");
        }
        if (value.length() != 4) {
            throw new IllegalArgumentException("Ticker must be 4 characters: " + value);
        }

        if (!Character.isDigit(value.charAt(0))) {
            throw new IllegalArgumentException("1st character must be a digit: " + value);
        }
        if (!Character.isDigit(value.charAt(2))) {
            throw new IllegalArgumentException("3rd character must be a digit: " + value);
        }

        validateAlphanumeric(value, 1);
        validateAlphanumeric(value, 3);

        this.value = value;
    }

    private static void validateAlphanumeric(String raw, int idx) {
        char c = raw.charAt(idx);
        if (Character.isDigit(c)) return;
        if (!Character.isUpperCase(c)) {
            throw new IllegalArgumentException("Alphabet must be uppercase letter: " + raw);
        }
        if (!ALLOWED_LETTERS.contains(c)) {
            throw new IllegalArgumentException(
                    "Alphabet must be one of " + ALLOWED_LETTERS + ": " + raw
            );
        }
    }
}
