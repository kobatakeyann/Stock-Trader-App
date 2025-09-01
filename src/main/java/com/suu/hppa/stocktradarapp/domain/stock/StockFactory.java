package com.suu.hppa.stocktradarapp.domain.stock;

import com.suu.hppa.stocktradarapp.domain.stock.exception.StockValidationException;

public interface StockFactory {

    Stock fromString(
            String tickerStr,
            String nameStr,
            String marketStr,
            String sharesIssuedStr) throws StockValidationException;
}
