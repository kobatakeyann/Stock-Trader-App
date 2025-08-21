package com.suu.hppa.summer_training.domain.stock;

import java.util.List;
import java.util.Optional;

public interface StockRepository {
    Optional<Stock> findByTicker(Ticker ticker);

    List<Stock> findAll();
}
