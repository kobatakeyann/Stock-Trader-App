package com.suu.hppa.summer_training.domain.stock;

import java.util.Optional;
import org.springframework.stereotype.Component;
import com.suu.hppa.summer_training.domain.stock.valueobject.Ticker;

@Component
public class StockDomainService {
    private final StockRepository stockRepository;

    public StockDomainService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public boolean isExists(Ticker id) {
        Optional<Stock> duplicatedStock = this.stockRepository.findByTicker(id);
        return duplicatedStock.isPresent();
    }
}
