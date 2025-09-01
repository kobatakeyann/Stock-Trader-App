package com.suu.hppa.stocktradarapp.presentation.cli.view;

import java.util.List;
import org.springframework.stereotype.Component;
import com.suu.hppa.stocktradarapp.presentation.cli.controller.DisplayMenuController;
import com.suu.hppa.stocktradarapp.presentation.cli.helper.table.TableColmunSpec;
import com.suu.hppa.stocktradarapp.presentation.cli.helper.table.TableMaker;
import com.suu.hppa.stocktradarapp.presentation.cli.viewmodel.stock.StockDisplayItem;

@Component
public class StockDisplayView {
    private static final List<TableColmunSpec> columns = List.of(
            new TableColmunSpec("Ticker", 6, TableColmunSpec.Alignment.LEFT),
            new TableColmunSpec("Product Name", 31, TableColmunSpec.Alignment.LEFT),
            new TableColmunSpec("Market", 8, TableColmunSpec.Alignment.LEFT),
            new TableColmunSpec("Shares Issued", 14, TableColmunSpec.Alignment.RIGHT)
    );

    private final DisplayMenuController displayMenuController;
    private final TableMaker<StockDisplayItem> tableMaker;

    public StockDisplayView(DisplayMenuController displayMenuController,
            TableMaker<StockDisplayItem> tableMaker) {
        this.displayMenuController = displayMenuController;
        this.tableMaker = tableMaker;
    }

    public void render() {
        System.out.println("銘柄マスタを表示します。");
        List<StockDisplayItem> stockRecords = this.displayMenuController.getAllStocks();
        String table = this.tableMaker.build(columns, stockRecords);
        System.out.println(table);
    }
}
