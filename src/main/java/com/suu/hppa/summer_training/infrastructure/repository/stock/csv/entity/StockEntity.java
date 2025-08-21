package com.suu.hppa.summer_training.infrastructure.repository.stock.csv.entity;

import com.suu.hppa.summer_training.domain.stock.Market;
import com.suu.hppa.summer_training.domain.stock.SharesIssued;
import com.suu.hppa.summer_training.domain.stock.Stock;
import com.suu.hppa.summer_training.domain.stock.StockName;
import com.suu.hppa.summer_training.domain.stock.Ticker;

// An entity class that represents a table structure in the csv file.
// The order of record params must match the one of the csv file.
public record StockEntity(String ticker, String productName, String market, String sharesIssued) {

    public static StockEntity fromCsvLine(String line, String delimiter) {
        String[] columns = line.split(delimiter);
        return new StockEntity(
                columns[0], columns[1], columns[2], columns[3]
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
}
