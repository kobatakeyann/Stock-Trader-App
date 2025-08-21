package com.suu.hppa.summer_training.domain.stock;

public enum Market {
    P("Prime"), S("Standard"), G("Growth");

    private final String marketName;

    private Market(String marketName) {
        this.marketName = marketName;
    }

    @Override
    public String toString() {
        return this.marketName;
    }
}
