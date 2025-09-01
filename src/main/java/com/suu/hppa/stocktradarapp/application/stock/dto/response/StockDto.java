package com.suu.hppa.stocktradarapp.application.stock.dto.response;

import com.suu.hppa.stocktradarapp.domain.stock.Stock;

public record StockDto(String ticker, String name, String market, long sharesIssued) {
    public static StockDto from(Stock stock) {
        return new StockDto(
                stock.ticker().value(),
                stock.name().value(),
                stock.market().toLongName(),
                stock.sharesIssued().value()
        );
    }
}
