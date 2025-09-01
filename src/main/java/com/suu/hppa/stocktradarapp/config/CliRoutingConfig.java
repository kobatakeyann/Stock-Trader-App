package com.suu.hppa.stocktradarapp.config;

import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.suu.hppa.stocktradarapp.presentation.cli.router.MenuRouter;
import com.suu.hppa.stocktradarapp.presentation.cli.view.ApplicationExitView;
import com.suu.hppa.stocktradarapp.presentation.cli.view.StockDisplayView;
import com.suu.hppa.stocktradarapp.presentation.cli.view.StockRegistrationView;
import com.suu.hppa.stocktradarapp.presentation.cli.viewmodel.menu.Menu;


@Configuration
public class CliRoutingConfig {
    @Bean
    public MenuRouter setRoutes(
            StockDisplayView stockDisplayView,
            StockRegistrationView stockRegistrationView,
            ApplicationExitView applicationExitView) {

        List<Menu> menus = List.of(
                new Menu("A", "銘柄マスター一覧表示"),
                new Menu("B", "銘柄マスター新規登録"),
                new Menu("Q", "アプリケーションを終了する")
        );

        Map<String, Runnable> routes = Map.of(
                "A", stockDisplayView::render,
                "B", stockRegistrationView::render,
                "Q", applicationExitView::render
        );

        return new MenuRouter(menus, routes);
    }
}
