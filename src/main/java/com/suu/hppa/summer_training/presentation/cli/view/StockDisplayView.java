package com.suu.hppa.summer_training.presentation.cli.view;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.suu.hppa.summer_training.presentation.cli.controller.DisplayMenuController;
import com.suu.hppa.summer_training.presentation.cli.viewmodel.StockItem;
import com.suu.hppa.summer_training.presentation.cli.viewmodel.TableColumn;

@Component
public class StockDisplayView {
    private final DisplayMenuController displayMenuController;
    private static final List<TableColumn> columns = List.of(
            new TableColumn("Ticker", 6, TableColumn.Alignment.LEFT),
            new TableColumn("Product Name", 31, TableColumn.Alignment.LEFT),
            new TableColumn("Market", 8, TableColumn.Alignment.LEFT),
            new TableColumn("Shares Issued", 14, TableColumn.Alignment.RIGHT)
    );


    public StockDisplayView(DisplayMenuController displayMenuController) {
        this.displayMenuController = displayMenuController;
    }

    public void render() {
        System.out.println("銘柄マスタを表示します。");

        List<StockItem> stockRecords = this.displayMenuController.getAllStocks();
        String table = buildTable(columns, stockRecords);
        System.out.println(table);
    }

    private String buildTable(
            List<TableColumn> columns,
            List<StockItem> records) {

        int contentTotalWidth = this.calculateTotalWidth(columns);

        return String.join(
                "\n",
                this.buildBorderLine(contentTotalWidth),
                this.buildHeaderLine(columns),
                this.buildSeparatorLine(columns),
                this.buildContentLines(records, columns),
                this.buildBorderLine(contentTotalWidth)
        );
    }

    private int calculateTotalWidth(List<TableColumn> columns) {
        return columns.stream()
                .mapToInt(col -> col.contentLength() + 2) // +2 for paddings of each column
                .sum() + columns.size() - 1; // between columns
    }

    private String buildBorderLine(int contentTotalWidth) {
        return "|" + "=".repeat(contentTotalWidth) + "|";
    }

    private String buildHeaderLine(List<TableColumn> columns) {
        return columns.stream()
                .map(col -> String.format(" " + "%-" + col.contentLength() + "s", col.name()) + " ")
                .collect(Collectors.joining("|", "|", "|"));
    }

    private String buildSeparatorLine(List<TableColumn> columns) {
        return columns.stream()
                .map(col -> "-".repeat(col.contentLength() + 2)) // +2 for paddings of each column
                .collect(Collectors.joining("+", "|", "|"));
    }

    private String buildContentLines(List<StockItem> records, List<TableColumn> columns) {
        return records.stream()
                .map(
                        stock -> columns.stream()
                                .map(col -> {
                                    String content = col.wrapContentIfOverWidth(
                                            stock.getElement(col.name())
                                    );
                                    return col.formatContent(content);
                                })
                                .collect(Collectors.joining("|", "|", "|"))
                )
                .collect(Collectors.joining("\n"));
    }
}
