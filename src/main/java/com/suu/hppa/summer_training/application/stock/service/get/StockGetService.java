package com.suu.hppa.summer_training.application.stock.service.get;

import java.util.List;
import org.springframework.stereotype.Service;
import com.suu.hppa.summer_training.application.stock.dto.StockDto;
import com.suu.hppa.summer_training.domain.stock.StockRepository;

@Service
public class StockGetService {
    private final StockRepository stockRepository;

    public StockGetService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<StockDto> getAllStocks() {
        return this.stockRepository.findAll().stream()
                .map(StockDto::from)
                .toList();
    }
}
