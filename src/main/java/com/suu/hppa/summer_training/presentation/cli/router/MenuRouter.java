package com.suu.hppa.summer_training.presentation.cli.router;

import static java.util.Map.entry;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.suu.hppa.summer_training.presentation.cli.view.StockDisplayView;
import com.suu.hppa.summer_training.presentation.cli.viewmodel.Menu;

@Component
public class MenuRouter {
    private final List<Menu> menus;
    private final Map<String, Runnable> routes;
    private final StockDisplayView stockDisplayView;

    public MenuRouter(StockDisplayView stockDisplayView) {
        this.stockDisplayView = stockDisplayView;
        this.menus = this.defineMenus();
        this.routes = this.setRoutes();
    }

    private Map<String, Runnable> setRoutes() {
        return Map.ofEntries(
                entry("A", this.stockDisplayView::render),
                entry("B", () -> System.out.println("B is called.")),
                entry("Q", () -> {
                    System.out.println("アプリケーションを終了します。");
                    System.exit(0);
                })
        );
    }

    private List<Menu> defineMenus() {
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
