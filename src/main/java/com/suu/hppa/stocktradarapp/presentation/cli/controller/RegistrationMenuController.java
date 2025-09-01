package com.suu.hppa.stocktradarapp.presentation.cli.controller;

import org.springframework.stereotype.Component;
import com.suu.hppa.stocktradarapp.application.common.helper.RequestResult;
import com.suu.hppa.stocktradarapp.application.stock.service.usecase.register.StockRegistrationService;
import com.suu.hppa.stocktradarapp.domain.stock.exception.StockValidationException;
import com.suu.hppa.stocktradarapp.domain.stock.valueobject.Market;
import com.suu.hppa.stocktradarapp.domain.stock.valueobject.SharesIssued;
import com.suu.hppa.stocktradarapp.domain.stock.valueobject.StockName;
import com.suu.hppa.stocktradarapp.domain.stock.valueobject.Ticker;
import com.suu.hppa.stocktradarapp.presentation.cli.helper.validation.ValidationResult;
import com.suu.hppa.stocktradarapp.presentation.cli.viewmodel.stock.StockDisplayItem;

@Component
public class RegistrationMenuController {

    private final StockRegistrationService stockRegistrationService;

    public RegistrationMenuController(StockRegistrationService stockRegistrationService) {
        this.stockRegistrationService = stockRegistrationService;
    }

    public ValidationResult validateName(String input) {
        try {
            StockName.validate(input);
            return ValidationResult.ok(input);
        } catch (StockValidationException e) {
            return ValidationResult.error(input, e.getMessage());
        }
    }

    public ValidationResult validateTicker(String input) {
        try {
            Ticker.validate(input);
            return ValidationResult.ok(input);
        } catch (StockValidationException e) {
            return ValidationResult.error(input, e.getMessage());
        }
    }

    public ValidationResult validateMarket(String input) {
        try {
            Market.fromLongName(input);
            return ValidationResult.ok(input);
        } catch (StockValidationException e) {
            return ValidationResult.error(input, e.getMessage());
        }
    }

    public ValidationResult validateSharesIssued(String input) {
        try {
            SharesIssued.fromStr(input);
            return ValidationResult.ok(input);
        } catch (StockValidationException e) {
            return ValidationResult.error(input, e.getMessage());
        }
    }

    public RequestResult register(StockDisplayItem item) {
        return this.stockRegistrationService.register(item.toCommand());
    }
}
