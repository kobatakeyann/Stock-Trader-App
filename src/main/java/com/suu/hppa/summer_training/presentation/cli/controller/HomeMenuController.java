package com.suu.hppa.summer_training.presentation.cli.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import com.suu.hppa.summer_training.presentation.cli.router.HomeMenuRouter;
import com.suu.hppa.summer_training.presentation.cli.viewmodel.Menu;

@Component
public class HomeMenuController {
    private final List<Menu> menuRoutes;

    public HomeMenuController(HomeMenuRouter homeMenuRouter) {
        this.menuRoutes = homeMenuRouter.getMenuRoutes();
    }

    public Optional<Menu> findRenderer(String userMenuSelect) {
        return this.menuRoutes.stream()
                .filter(menu -> menu.keyAlphabet().equals(userMenuSelect.toUpperCase()))
                .findFirst();
    }
}
