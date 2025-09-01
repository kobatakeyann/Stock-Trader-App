package com.suu.hppa.stocktradarapp.domain.stock.valueobject;

import java.util.Arrays;
import com.suu.hppa.stocktradarapp.domain.stock.exception.StockValidationException;

public enum Market {
    P("Prime"), S("Standard"), G("Growth");

    private final String marketName;

    private Market(String marketName) {
        this.marketName = marketName;
    }

    public static Market fromLongName(String marketName) {
        if (marketName == null) {
            throw new StockValidationException("Value of market is null.");
        }
        Market[] enumElems = Market.values();
        return Arrays.stream(enumElems)
                .filter(i -> i.marketName.equalsIgnoreCase(marketName))
                .findFirst()
                .orElseThrow(
                        () -> new StockValidationException(
                                "Designated value is not defined in the market enum class: "
                                        + marketName
                        )
                );
    }

    public String toLongName() {
        return this.marketName;
    }
}
