package com.suu.hppa.stocktradarapp.infrastructure.factory.stock.command;

import org.springframework.stereotype.Component;
import com.suu.hppa.stocktradarapp.domain.stock.Stock;
import com.suu.hppa.stocktradarapp.domain.stock.StockFactory;
import com.suu.hppa.stocktradarapp.domain.stock.exception.StockValidationException;
import com.suu.hppa.stocktradarapp.domain.stock.valueobject.Market;
import com.suu.hppa.stocktradarapp.domain.stock.valueobject.SharesIssued;
import com.suu.hppa.stocktradarapp.domain.stock.valueobject.StockName;
import com.suu.hppa.stocktradarapp.domain.stock.valueobject.Ticker;

@Component
public class CliStockFactory implements StockFactory {
    @Override
    public Stock fromString(
            String tickerStr,
            String nameStr,
            String marketStr,
            String sharesIssuedStr) throws StockValidationException {
        Ticker ticker = new Ticker(tickerStr);
        StockName stockName = new StockName(nameStr);
        Market market = Market.fromLongName(marketStr);
        SharesIssued sharesIssued = SharesIssued.fromStr(sharesIssuedStr);
        return new Stock(ticker, stockName, market, sharesIssued);
    }

}
