package com.suu.hppa.stocktradarapp.infrastructure.repository.stock.csv.entity;

import com.suu.hppa.stocktradarapp.domain.stock.Stock;
import com.suu.hppa.stocktradarapp.domain.stock.valueobject.Market;
import com.suu.hppa.stocktradarapp.domain.stock.valueobject.SharesIssued;
import com.suu.hppa.stocktradarapp.domain.stock.valueobject.StockName;
import com.suu.hppa.stocktradarapp.domain.stock.valueobject.Ticker;

// An entity class that represents a table structure in the csv file.
// The order of record params must match the one of the csv file.
public record StockEntity(String ticker, String productName, String market, String sharesIssued) {

    public static StockEntity fromCsvLine(String line, String delimiter) {
        String[] columns = line.split(delimiter);
        return new StockEntity(
                columns[0], columns[1], columns[2], columns[3]
        );
    }

    public static StockEntity fromModel(Stock stock) {
        return new StockEntity(
                stock.ticker().value(),
                stock.name().value(),
                stock.market().toLongName(),
                String.valueOf(stock.sharesIssued())
        );
    }

    public Stock toModel() {
        return new Stock(
                new Ticker(ticker),
                new StockName(productName),
                Market.valueOf(market),
                new SharesIssued(Long.parseLong(sharesIssued))
        );
    }

    public String toCsvLine(String delimiter) {
        return String.join(
                delimiter,
                this.ticker,
                this.productName,
                Market.fromLongName(market).name(),
                this.sharesIssued
        );
    }
}
