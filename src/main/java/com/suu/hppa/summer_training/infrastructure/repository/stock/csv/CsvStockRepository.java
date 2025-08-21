package com.suu.hppa.summer_training.infrastructure.repository.stock.csv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import com.suu.hppa.summer_training.domain.stock.Stock;
import com.suu.hppa.summer_training.domain.stock.StockRepository;
import com.suu.hppa.summer_training.domain.stock.Ticker;
import com.suu.hppa.summer_training.infrastructure.repository.stock.csv.entity.StockEntity;
import com.suu.hppa.summer_training.infrastructure.repository.stock.csv.specification.CsvSpecification;
import com.suu.hppa.summer_training.infrastructure.repository.stock.csv.validation.CsvValidator;
import com.suu.hppa.summer_training.infrastructure.repository.stock.csv.validation.EntityValidator;

@Repository
public class CsvStockRepository implements StockRepository {
    private final Resource csvResource;

    public CsvStockRepository() {
        CsvValidator.checkIfCsvExists();
        CsvValidator.checkCsvColumns();
        EntityValidator.validateEntityParams(StockEntity.class);
        this.csvResource = new ClassPathResource(CsvSpecification.FILEPATH);
    }

    @Override
    public List<Stock> findAll() {
        try (var reader = new BufferedReader(
                new InputStreamReader(csvResource.getInputStream(), CsvSpecification.ENCODING)
        )) {
            return reader.lines()
                    .skip(1) // skip header line
                    .map(line -> StockEntity.fromCsvLine(line, CsvSpecification.CSV_DELIMITER))
                    .map(StockEntity::toModel)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read csv file.", e);
        }
    }

    @Override
    public Optional<Stock> findByTicker(Ticker ticker) {
        try (var reader = new BufferedReader(
                new InputStreamReader(csvResource.getInputStream(), CsvSpecification.ENCODING)
        )) {
            return reader.lines()
                    .skip(1) // skip header line
                    .map(line -> StockEntity.fromCsvLine(line, CsvSpecification.CSV_DELIMITER))
                    .map(StockEntity::toModel)
                    .filter(record -> new Ticker(record.ticker()).equals(ticker))
                    .findFirst();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read csv file.", e);
        }
    }
}
