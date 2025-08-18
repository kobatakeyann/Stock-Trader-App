package com.suu.hppa.summer_training.presentation.cli.view;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.suu.hppa.summer_training.presentation.cli.controller.HomeMenuController;
import com.suu.hppa.summer_training.presentation.cli.router.HomeMenuRouter;
import com.suu.hppa.summer_training.presentation.cli.viewmodel.Menu;

@Component
public class HomeMenuView {
    private final Scanner stdInScanner = new Scanner(System.in);
    private final HomeMenuController homeMenuController;
    private final List<String> menuLabels;

    public HomeMenuView(HomeMenuController homeMenuController, HomeMenuRouter homeMenuRouter) {
        this.homeMenuController = homeMenuController;
        this.menuLabels = homeMenuRouter.getMenuLabels();
    }

    public void show() {
        System.out.println("株式取引管理システムを開始します");
        while (true) {
            System.out.println("操作するメニューを選んでください。");
            this.menuLabels.stream().forEach(System.out::println);

            System.out.print("入力してください: ");
            String userInput = this.stdInScanner.nextLine().trim();
            Optional<Menu> selectedMenu = homeMenuController.findRenderer(userInput);

            if (selectedMenu.isPresent()) {
                Menu targetMenu = selectedMenu.get();
                System.out.printf("「%s」が選択されました。%n", targetMenu.name());
                targetMenu.renderer().run();
            } else {
                System.out.println(userInput + "に対応するメニューは存在しません。");
            }
            System.out.println("---");
        }
    }
}
