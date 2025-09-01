package com.suu.hppa.stocktradarapp.application.stock.service.usecase.register;

import org.springframework.stereotype.Service;
import com.suu.hppa.stocktradarapp.application.common.helper.RequestResult;
import com.suu.hppa.stocktradarapp.application.stock.dto.request.StockRegistrationCommand;
import com.suu.hppa.stocktradarapp.application.stock.dto.response.StockDto;
import com.suu.hppa.stocktradarapp.domain.stock.Stock;
import com.suu.hppa.stocktradarapp.domain.stock.StockDomainService;
import com.suu.hppa.stocktradarapp.domain.stock.StockFactory;
import com.suu.hppa.stocktradarapp.domain.stock.StockRepository;
import com.suu.hppa.stocktradarapp.domain.stock.exception.StockValidationException;
import com.suu.hppa.stocktradarapp.domain.stock.valueobject.Ticker;

@Service
public class StockRegistrationService {
    private final StockRepository stockRepository;
    private final StockDomainService stockDomainService;
    private final StockFactory stockFactory;

    public StockRegistrationService(
            StockRepository stockRepository,
            StockDomainService stockDomainService,
            StockFactory stockFactory) {
        this.stockRepository = stockRepository;
        this.stockDomainService = stockDomainService;
        this.stockFactory = stockFactory;
    }

    public RequestResult register(StockRegistrationCommand cmd) {
        Ticker targetId = new Ticker(cmd.ticker());
        if (this.stockDomainService.isExists(targetId)) {
            return RequestResult.fail("Ticker already used: " + targetId);
        }

        Stock newStock;
        try {
            newStock = this.stockFactory
                    .fromString(cmd.ticker(), cmd.name(), cmd.market(), cmd.sharesIssued());
        } catch (StockValidationException e) {
            return RequestResult.fail(e.getMessage());
        }

        this.stockRepository.save(newStock);
        return RequestResult.success(StockDto.from(newStock));
    }
}
