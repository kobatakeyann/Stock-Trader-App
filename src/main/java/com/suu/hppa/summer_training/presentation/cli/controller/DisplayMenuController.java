package com.suu.hppa.summer_training.presentation.cli.controller;

import java.util.List;
import org.springframework.stereotype.Component;
import com.suu.hppa.summer_training.application.stock.service.get.StockGetService;
import com.suu.hppa.summer_training.presentation.cli.viewmodel.StockItem;

@Component
public class DisplayMenuController {
    private final StockGetService stockGetService;

    public DisplayMenuController(StockGetService stockGetService) {
        this.stockGetService = stockGetService;
    }

    public List<StockItem> getAllStocks() {
        return this.stockGetService.getAllStocks().stream()
                .map(StockItem::from)
                .toList();
    }
}
