package com.suu.hppa.stocktradarapp.domain.stock;

import java.util.List;
import java.util.Optional;
import com.suu.hppa.stocktradarapp.domain.stock.valueobject.Ticker;

public interface StockRepository {
    Optional<Stock> findByTicker(Ticker ticker);

    List<Stock> findAll();

    void save(Stock stock);
}
