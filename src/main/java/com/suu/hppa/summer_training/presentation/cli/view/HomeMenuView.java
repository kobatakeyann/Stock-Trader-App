package com.suu.hppa.summer_training.presentation.cli.view;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.suu.hppa.summer_training.presentation.cli.controller.HomeMenuController;
import com.suu.hppa.summer_training.presentation.cli.dto.ExecutableMenuItem;

@Component
public class HomeMenuView {
    private final Scanner stdInScanner = new Scanner(System.in);
    private final HomeMenuController homeMenuController;
    private final List<String> menuLabels;

    public HomeMenuView(HomeMenuController homeMenuController) {
        this.homeMenuController = homeMenuController;
        this.menuLabels = homeMenuController.getMenuLables();
    }

    public void render() {
        while (true) {
            System.out.println("操作するメニューを選んでください。");
            this.menuLabels.stream().forEach(System.out::println);

            System.out.print("入力してください: ");
            String userInput = this.stdInScanner.nextLine().trim();
            System.out.println(userInput);

            Optional<ExecutableMenuItem> selectedMenuProvider =
                    this.homeMenuController.findExecutableMenuItem(userInput);
            if (selectedMenuProvider.isEmpty()) {
                System.out.println(userInput + "に対応するメニューは存在しません。");
                continue;
            }
            String selectedMenuName = selectedMenuProvider.get().menu().name();
            Runnable renderer = selectedMenuProvider.get().renderer();

            System.out.printf("「%s」が選択されました。%n", selectedMenuName);
            renderer.run();
            System.out.println("---");
        }
    }
}
