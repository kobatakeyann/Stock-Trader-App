package com.suu.hppa.summer_training.presentation.cli.router;

import static java.util.Map.entry;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.suu.hppa.summer_training.presentation.cli.viewmodel.Menu;

@Component
public class MenuRouter {
    private final List<Menu> menus = defineMenus();
    private final Map<String, Runnable> routes = setRoutes();

    private static Map<String, Runnable> setRoutes() {
        return Map.ofEntries(
                entry("A", () -> System.out.println("A is called.")),
                entry("B", () -> System.out.println("B is called.")),
                entry("Q", () -> {
                    System.out.println("アプリケーションを終了します。");
                    System.exit(0);
                })
        );
    }

    private static List<Menu> defineMenus() {
        return List.of(
                new Menu("A", "銘柄マスター一覧表示"),
                new Menu("B", "銘柄マスター新規登録"),
                new Menu("Q", "アプリケーションを終了する")
        );
    }


    public List<Menu> getMenus() {
        return Collections.unmodifiableList(this.menus);
    }

    public Map<String, Runnable> getRoutes() {
        return Collections.unmodifiableMap(this.routes);
    }
}
