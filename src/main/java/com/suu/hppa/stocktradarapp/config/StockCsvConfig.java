package com.suu.hppa.stocktradarapp.config;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.stock.csv")
public class StockCsvConfig {
    private String path;
    private String encoding;
    private String delimiter;
    private List<String> columns;
    private List<String> columnsCamelCase;

    public StockCsvConfig() {}

    public void setPath(String path) {
        this.path = path;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public void setColumnsCamelCase(List<String> columnsCamelCase) {
        this.columnsCamelCase = columnsCamelCase;
    }

    public String getPath() {
        return this.path;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public String getDelimiter() {
        return this.delimiter;
    }

    public List<String> getColumns() {
        return this.columns;
    }

    public List<String> getColumnsCamelCase() {
        return this.columnsCamelCase;
    }
}
