package com.suu.hppa.summer_training.infrastructure.repository.stock.csv.specification;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

public class CsvSpecification {
        public static final String CSV_DELIMITER = ",";
        public static final Charset ENCODING = StandardCharsets.UTF_8;
        public static final String FILEPATH = Path.of("data/stock_master.csv").toString();

        public static final List<String> COLUMNS = List.of(
                        "ticker",
                        "product_name",
                        "market",
                        "shares_issued"
        );

        public static final List<String> COLUMNS_CAMEL_CASE = List.of(
                        "ticker",
                        "productName",
                        "market",
                        "sharesIssued"
        );
}
