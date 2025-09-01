package com.suu.hppa.stocktradarapp.presentation.cli.viewmodel.stock;

import com.suu.hppa.stocktradarapp.application.stock.dto.request.StockRegistrationCommand;
import com.suu.hppa.stocktradarapp.application.stock.dto.response.StockDto;
import com.suu.hppa.stocktradarapp.presentation.cli.helper.table.TableRenderable;

public record StockDisplayItem(
        String ticker,
        String productName,
        String market,
        String sharesIssued) implements TableRenderable {

    public static StockDisplayItem from(StockDto stockDto) {
        return new StockDisplayItem(
                stockDto.ticker(),
                stockDto.name(),
                stockDto.market(),
                String.format("%,d", stockDto.sharesIssued())
        );
    }

    public StockRegistrationCommand toCommand() {
        return new StockRegistrationCommand(
                this.ticker,
                this.productName,
                this.market,
                this.sharesIssued
        );
    }

    @Override
    public String getField(String colName) {
        return switch (colName) {
            case "Ticker" -> ticker;
            case "Product Name" -> productName;
            case "Market" -> market;
            case "Shares Issued" -> sharesIssued;
            default -> throw new IllegalArgumentException("Invalid parameter name: " + colName);
        };
    }
}
