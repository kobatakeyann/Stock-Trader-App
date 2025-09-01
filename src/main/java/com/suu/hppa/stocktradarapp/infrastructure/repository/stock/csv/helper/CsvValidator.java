package com.suu.hppa.stocktradarapp.infrastructure.repository.stock.csv.helper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import com.suu.hppa.stocktradarapp.config.StockCsvConfig;

@Component
public class CsvValidator {
    private final StockCsvConfig config;
    private final Resource csvResource;
    private final Charset charset;

    public CsvValidator(StockCsvConfig config) {
        this.config = config;
        this.csvResource = new FileSystemResource(config.getPath());
        this.charset = Charset.forName(config.getEncoding());
    }

    public void checkIfCsvExists() {
        if (this.csvResource.exists()) return;
        throw new RuntimeException(
                "Csv file does not exist at expected path: " + config.getPath()
        );
    }

    public void checkCsvColumns() {
        List<String> csvHeaderCols = this.getCsvHeaderCols();
        List<String> specificationCols = this.config.getColumns();
        if (csvHeaderCols.size() != specificationCols.size()) {
            throw new IllegalArgumentException(
                    "Number of CSV columns does not match the expected columns: expected: "
                            + specificationCols.size() + ", gotten: " + csvHeaderCols.size()
            );
        }
        if (!specificationCols.equals(csvHeaderCols)) {
            throw new IllegalArgumentException(
                    "CSV column elements does not match the expected ones: expected: "
                            + specificationCols + ", gotten: " + csvHeaderCols
            );
        }
    }

    private List<String> getCsvHeaderCols() {
        try (var reader = Files.newBufferedReader(
                this.csvResource.getFile().toPath(), this.charset
        )) {
            String headerLine = reader.readLine();
            if (headerLine == null) {
                throw new IllegalArgumentException(
                        "csv file is empty: " + this.csvResource.getFilename()
                );
            }
            return List.of(headerLine.split(config.getDelimiter()));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read csv file.", e);
        }
    }


}
