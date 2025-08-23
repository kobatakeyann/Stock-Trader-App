package com.suu.hppa.summer_training.domain.stock.valueobject;

public enum Market {
    P("Prime"), S("Standard"), G("Growth");

    private final String marketName;

    private Market(String marketName) {
        this.marketName = marketName;
    }

    public static Market fromStr(String enumValue) {
        if (enumValue == null) {
            throw new IllegalArgumentException("Value of market is null.");
        }
        try {
            return Market.valueOf(enumValue);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "Designated value is not defined in the market enum class: " + enumValue, e
            );
        }
    }

    @Override
    public String toString() {
        return this.marketName;
    }
}
