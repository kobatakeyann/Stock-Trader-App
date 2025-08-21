package com.suu.hppa.summer_training.infrastructure.repository.stock.csv.validation;

import java.lang.reflect.RecordComponent;
import java.util.Arrays;
import java.util.List;
import com.suu.hppa.summer_training.infrastructure.repository.stock.csv.specification.CsvSpecification;

public class EntityValidator {
    public static void validateEntityParams(Class<?> recordEntityClass) {
        if (!recordEntityClass.isRecord()) {
            throw new IllegalArgumentException(
                    recordEntityClass.getName() + " is not a record entity."
            );
        }
        RecordComponent[] entityParams = recordEntityClass.getRecordComponents();
        List<String> entityColNames = Arrays.stream(entityParams)
                .map(RecordComponent::getName)
                .toList();
        final List<String> CSV_CAMEL_COLUMNS = CsvSpecification.COLUMNS_CAMEL_CASE;
        if (CSV_CAMEL_COLUMNS.equals(entityColNames)) return;
        throw new IllegalStateException(
                "Entity class: " + recordEntityClass.getName()
                        + "Record parameters of are not consistent with csv column order."
        );
    }

}
