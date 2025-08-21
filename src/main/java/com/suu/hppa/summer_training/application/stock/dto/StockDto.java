package com.suu.hppa.summer_training.application.stock.dto;

import com.suu.hppa.summer_training.domain.stock.Market;
import com.suu.hppa.summer_training.domain.stock.Stock;

public record StockDto(String ticker, String name, Market market, long sharesIssued) {
    public static StockDto from(Stock stock) {
        return new StockDto(
                stock.ticker(),
                stock.name(),
                stock.market(),
                stock.sharesIssued()
        );
    }
}
