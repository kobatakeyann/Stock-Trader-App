package com.suu.hppa.summer_training.domain.stock;

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

    public String ticker() {
        return this.ticker.value();
    }

    public String name() {
        return this.name.value();
    }

    public Market market() {
        return this.market;
    }

    public long sharesIssued() {
        return this.sharesIssued.value();
    }
}
