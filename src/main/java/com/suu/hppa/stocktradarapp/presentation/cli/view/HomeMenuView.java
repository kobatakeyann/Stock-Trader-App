package com.suu.hppa.stocktradarapp.presentation.cli.view;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.suu.hppa.stocktradarapp.presentation.cli.controller.HomeMenuController;
import com.suu.hppa.stocktradarapp.presentation.cli.viewmodel.menu.ExecutableMenuItem;

@Component
public class HomeMenuView {
    private final Scanner stdInScanner;
    private final HomeMenuController homeMenuController;

    public HomeMenuView(Scanner stdInScanner, HomeMenuController homeMenuController) {
        this.stdInScanner = stdInScanner;
        this.homeMenuController = homeMenuController;
    }

    public void render() {
        while (true) {
            System.out.println("操作するメニューを選んでください。");
            this.showMenus();

            System.out.print("入力してください: ");
            String userInput = this.stdInScanner.nextLine().trim();

            Optional<ExecutableMenuItem> selectedMenuProvider =
                    this.homeMenuController.findExecutableMenuItem(userInput);

            selectedMenuProvider.ifPresentOrElse(
                    menuItem -> {
                        System.out.printf("「%s」が選択されました。%n", menuItem.menu().name());
                        menuItem.renderer().run();
                    },
                    () -> System.out.printf("'%s' に対応するメニューは存在しません。%n", userInput)
            );
            System.out.println("---");
        }
    }

    private void showMenus() {
        List<String> menuLabels = this.homeMenuController.getMenuLabels();
        menuLabels.stream().forEach(System.out::println);
    }
}
