package com.suu.hppa.summer_training.presentation.cli.viewmodel;

import com.suu.hppa.summer_training.application.stock.dto.StockDto;

public record StockItem(
        String ticker,
        String productName,
        String market,
        String sharesIssued) {
    public static StockItem from(StockDto stockDto) {
        return new StockItem(
                stockDto.ticker(),
                stockDto.name(),
                stockDto.market().toString(),
                String.format("%,d", stockDto.sharesIssued())
        );
    }

    public String getElement(String colName) {
        return switch (colName) {
            case "Ticker" -> ticker;
            case "Product Name" -> productName;
            case "Market" -> market;
            case "Shares Issued" -> sharesIssued;
            default -> throw new IllegalArgumentException("Invalid parameter name: " + colName);
        };
    }
}
