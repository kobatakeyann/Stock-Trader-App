package com.suu.hppa.stocktradarapp.application.stock.dto.request;

public record StockRegistrationCommand(
        String ticker,
        String name,
        String market,
        String sharesIssued) {
}
