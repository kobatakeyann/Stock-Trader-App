package com.suu.hppa.stocktradarapp.presentation.cli.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import com.suu.hppa.stocktradarapp.presentation.cli.router.MenuRouter;
import com.suu.hppa.stocktradarapp.presentation.cli.viewmodel.menu.ExecutableMenuItem;
import com.suu.hppa.stocktradarapp.presentation.cli.viewmodel.menu.Menu;

@Component
public class HomeMenuController {
    private final MenuRouter router;

    public HomeMenuController(MenuRouter router) {
        this.router = router;
    }

    public List<String> getMenuLabels() {
        return this.router.getMenus().stream().map(Menu::getLabel).toList();
    }

    public Optional<ExecutableMenuItem> findExecutableMenuItem(String keyAlphabet) {
        Optional<Menu> menu = this.findMenu(keyAlphabet);
        Optional<Runnable> renderer = this.findRenderer(keyAlphabet);
        if (menu.isPresent() && renderer.isPresent()) {
            return Optional.of(new ExecutableMenuItem(menu.get(), renderer.get()));
        }
        return Optional.empty();
    }

    private Optional<Menu> findMenu(String keyAlphabet) {
        return this.router.getMenus().stream()
                .filter(menu -> menu.keyAlphabet().equalsIgnoreCase(keyAlphabet))
                .findFirst();
    }

    private Optional<Runnable> findRenderer(String keyAlphabet) {
        return Optional.ofNullable(this.router.getRoutes().get(keyAlphabet.toUpperCase()));
    }
}
