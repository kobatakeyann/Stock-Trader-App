package com.suu.hppa.stocktradarapp.presentation.cli.view;

import java.util.Scanner;
import java.util.function.Function;
import org.springframework.stereotype.Component;
import com.suu.hppa.stocktradarapp.application.common.helper.RequestResult;
import com.suu.hppa.stocktradarapp.application.common.helper.RequestStatus;
import com.suu.hppa.stocktradarapp.presentation.cli.controller.RegistrationMenuController;
import com.suu.hppa.stocktradarapp.presentation.cli.exception.UserQuitException;
import com.suu.hppa.stocktradarapp.presentation.cli.helper.validation.ValidationResult;
import com.suu.hppa.stocktradarapp.presentation.cli.helper.validation.ValidationStatus;
import com.suu.hppa.stocktradarapp.presentation.cli.viewmodel.stock.StockDisplayItem;

@Component
public class StockRegistrationView {
    private final Scanner stdInScanner;
    private final RegistrationMenuController registrationMenuController;

    public StockRegistrationView(
            Scanner stdInScanner,
            RegistrationMenuController registrationMenuController) {
        this.stdInScanner = stdInScanner;
        this.registrationMenuController = registrationMenuController;
    }

    public void render() {
        System.out.println("新規株式銘柄マスタを登録します。");
        System.out.println("登録を中止するには、'quit'と入力してください。");

        try {
            String name = this.promptAndValidate(
                    "銘柄名> ", input -> this.registrationMenuController.validateName(input)
            );
            String ticker = this.promptAndValidate(
                    "銘柄コード> ", input -> this.registrationMenuController.validateTicker(input)
            );
            String market = this.promptAndValidate(
                    "上場市場> ", input -> this.registrationMenuController.validateMarket(input)
            );
            String sharesIssued = this.promptAndValidate(
                    "発行済み株式数> ",
                    input -> this.registrationMenuController.validateSharesIssued(input)
            );

            StockDisplayItem item = new StockDisplayItem(ticker, name, market, sharesIssued);

            RequestResult result = this.registrationMenuController.register(item);
            if (result.status() != RequestStatus.SUCCESS) {
                System.out.println("新規銘柄の登録に失敗しました: " + result.message());
                return;
            }
            System.out.println(name + "を新規銘柄として登録しました。");
        } catch (UserQuitException e) {
            System.out.println("登録を中止しました。");
        }
    }


    private String promptAndValidate(String prompt, Function<String, ValidationResult> validator) {
        while (true) {
            System.out.print(prompt);
            String userInput = this.stdInScanner.nextLine();
            if (userInput.equalsIgnoreCase("quit")) {
                throw new UserQuitException("User requested to quit.");
            }
            ValidationResult validated = validator.apply(userInput);
            if (validated.status() == ValidationStatus.OK) {
                return validated.value();
            } else {
                System.out.println(validated.errorMessage());
            }
        }
    }
}
