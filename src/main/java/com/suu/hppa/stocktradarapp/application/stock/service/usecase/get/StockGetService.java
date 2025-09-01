package com.suu.hppa.stocktradarapp.application.stock.service.usecase.get;

import java.util.List;

import org.springframework.stereotype.Service;

import com.suu.hppa.stocktradarapp.application.stock.dto.response.StockDto;
import com.suu.hppa.stocktradarapp.domain.stock.StockRepository;

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
