package com.suu.hppa.stocktradarapp.infrastructure.repository.stock.csv.helper;

import java.lang.reflect.RecordComponent;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Component;
import com.suu.hppa.stocktradarapp.config.StockCsvConfig;

@Component
public class EntityValidator {
    private final StockCsvConfig config;

    public EntityValidator(StockCsvConfig config) {
        this.config = config;
    }

    public void validateEntityParams(Class<?> recordEntityClass) {
        if (!recordEntityClass.isRecord()) {
            throw new IllegalArgumentException(
                    recordEntityClass.getName() + " is not a record entity."
            );
        }
        RecordComponent[] entityParams = recordEntityClass.getRecordComponents();
        List<String> entityColNames = Arrays.stream(entityParams)
                .map(RecordComponent::getName)
                .toList();
        final List<String> CSV_CAMEL_COLUMNS = this.config.getColumnsCamelCase();
        if (CSV_CAMEL_COLUMNS.equals(entityColNames)) return;
        throw new IllegalStateException(
                "Entity class: " + recordEntityClass.getName()
                        + "Record parameters are not consistent with csv column order."
        );
    }

}
