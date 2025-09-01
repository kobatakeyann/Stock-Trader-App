package com.suu.hppa.stocktradarapp.domain.stock;

import com.suu.hppa.stocktradarapp.domain.stock.valueobject.Market;
import com.suu.hppa.stocktradarapp.domain.stock.valueobject.SharesIssued;
import com.suu.hppa.stocktradarapp.domain.stock.valueobject.StockName;
import com.suu.hppa.stocktradarapp.domain.stock.valueobject.Ticker;

public class Stock {
    private final Ticker ticker;
    private final StockName name;
    private final Market market;
    private final SharesIssued sharesIssued;

    public Stock(
            Ticker ticker,
            StockName name,
            Market market,
            SharesIssued sharesIssued) {
        this.ticker = ticker;
        this.name = name;
        this.market = market;
        this.sharesIssued = sharesIssued;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Stock other)) return false;
        return this.ticker.equals(other.ticker);
    }

    @Override
    public int hashCode() {
        return this.ticker.hashCode();
    }

    public Ticker ticker() {
        return this.ticker;
    }

    public StockName name() {
        return this.name;
    }

    public Market market() {
        return this.market;
    }

    public SharesIssued sharesIssued() {
        return this.sharesIssued;
    }
}
