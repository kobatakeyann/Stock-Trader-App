package com.suu.hppa.stocktradarapp.infrastructure.repository.stock.csv;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Optional;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import com.suu.hppa.stocktradarapp.config.StockCsvConfig;
import com.suu.hppa.stocktradarapp.domain.stock.Stock;
import com.suu.hppa.stocktradarapp.domain.stock.StockRepository;
import com.suu.hppa.stocktradarapp.domain.stock.valueobject.Ticker;
import com.suu.hppa.stocktradarapp.infrastructure.repository.stock.csv.entity.StockEntity;
import com.suu.hppa.stocktradarapp.infrastructure.repository.stock.csv.helper.CsvValidator;
import com.suu.hppa.stocktradarapp.infrastructure.repository.stock.csv.helper.EntityValidator;

@Repository
public class CsvStockRepository implements StockRepository {
    private final Resource csvResource;
    private final StockCsvConfig config;
    private final Charset charset;

    public CsvStockRepository(
            StockCsvConfig config,
            CsvValidator csvValidator,
            EntityValidator entityValidator) {
        csvValidator.checkIfCsvExists();
        csvValidator.checkCsvColumns();
        entityValidator.validateEntityParams(StockEntity.class);
        this.csvResource = new FileSystemResource(config.getPath());
        this.config = config;
        this.charset = Charset.forName(config.getEncoding());
    }

    @Override
    public List<Stock> findAll() {
        try (var reader = Files.newBufferedReader(
                this.csvResource.getFile().toPath(), this.charset
        )) {
            return reader.lines()
                    .skip(1) // skip header line
                    .map(line -> StockEntity.fromCsvLine(line, this.config.getDelimiter()))
                    .map(StockEntity::toModel)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read csv file.", e);
        }
    }

    @Override
    public Optional<Stock> findByTicker(Ticker ticker) {
        try (var reader = Files.newBufferedReader(
                this.csvResource.getFile().toPath(), this.charset
        )) {
            return reader.lines()
                    .skip(1) // skip header line
                    .map(line -> StockEntity.fromCsvLine(line, this.config.getDelimiter()))
                    .map(StockEntity::toModel)
                    .filter(stock -> stock.ticker().equals(ticker))
                    .findFirst();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read csv file.", e);
        }
    }

    @Override
    public void save(Stock stock) {
        try (var writer = Files.newBufferedWriter(
                csvResource.getFile().toPath(),
                this.charset,
                StandardOpenOption.APPEND
        )) {
            String csvLine = StockEntity.fromModel(stock).toCsvLine(this.config.getDelimiter());
            writer.write(csvLine);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to csv file.", e);
        }
    }
}
