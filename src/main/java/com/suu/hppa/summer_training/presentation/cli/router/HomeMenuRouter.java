package com.suu.hppa.summer_training.presentation.cli.router;

import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;
import com.suu.hppa.summer_training.presentation.cli.viewmodel.Menu;

@Component
public class HomeMenuRouter {
    private final List<Menu> menuRoutes;

    public HomeMenuRouter() {
        this.menuRoutes =
                List.of(new Menu("A", "銘柄マスター一覧表示", () -> System.out.println("A is called.")),
                        new Menu("B", "銘柄マスタ新規登録", () -> System.out.println("B is called.")),
                        new Menu("Q", "アプリケーションを終了する", () -> {
                            System.out.println("アプリケーションを終了します。");
                            System.exit(0);
                        }));
    }

    public List<Menu> getMenuRoutes() {
        return Collections.unmodifiableList(this.menuRoutes);
    }

    public List<String> getMenuLabels() {
        return this.menuRoutes.stream().map(menu -> menu.keyAlphabet() + ": " + menu.name())
                .toList();
    }

    public List<String> getMenuNames() {
        return this.menuRoutes.stream().map(menu -> menu.name()).toList();
    }
}
