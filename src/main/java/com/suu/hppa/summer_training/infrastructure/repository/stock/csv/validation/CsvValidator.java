package com.suu.hppa.summer_training.infrastructure.repository.stock.csv.validation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import com.suu.hppa.summer_training.infrastructure.repository.stock.csv.specification.CsvSpecification;

public class CsvValidator {
    private static final Resource csvResource = new ClassPathResource(CsvSpecification.FILEPATH);

    public static void checkIfCsvExists() {
        if (csvResource.exists()) return;
        throw new RuntimeException(
                "Csv file does not exits at expected path: " + CsvSpecification.FILEPATH
        );
    }

    public static void checkCsvColumns() {
        List<String> csvHeaderCols = getCsvHeaderCols();
        List<String> specificationCols = CsvSpecification.COLUMNS;
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

    private static List<String> getCsvHeaderCols() {
        try (var reader = new BufferedReader(
                new InputStreamReader(csvResource.getInputStream(), CsvSpecification.ENCODING)
        )) {
            String headerLine = reader.readLine();
            if (headerLine == null) {
                throw new IllegalArgumentException(
                        "csv file is empty: " + CsvSpecification.FILEPATH
                );
            }
            return List.of(headerLine.split(CsvSpecification.CSV_DELIMITER));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read csv file.", e);
        }
    }


}
