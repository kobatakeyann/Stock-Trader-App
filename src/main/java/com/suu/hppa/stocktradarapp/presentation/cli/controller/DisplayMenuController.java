package com.suu.hppa.stocktradarapp.presentation.cli.controller;

import java.util.List;
import org.springframework.stereotype.Component;
import com.suu.hppa.stocktradarapp.application.stock.service.usecase.get.StockGetService;
import com.suu.hppa.stocktradarapp.presentation.cli.viewmodel.stock.StockDisplayItem;

@Component
public class DisplayMenuController {
    private final StockGetService stockGetService;

    public DisplayMenuController(StockGetService stockGetService) {
        this.stockGetService = stockGetService;
    }

    public List<StockDisplayItem> getAllStocks() {
        return this.stockGetService.getAllStocks().stream()
                .map(StockDisplayItem::from)
                .toList();
    }
}
