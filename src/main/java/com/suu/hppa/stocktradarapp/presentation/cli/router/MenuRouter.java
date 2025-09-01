package com.suu.hppa.stocktradarapp.presentation.cli.router;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import com.suu.hppa.stocktradarapp.presentation.cli.viewmodel.menu.Menu;

public class MenuRouter {
    private final List<Menu> menus;
    private final Map<String, Runnable> routes;

    public MenuRouter(List<Menu> menus, Map<String, Runnable> routes) {
        this.menus = menus;
        this.routes = routes;
    }

    public List<Menu> getMenus() {
        return Collections.unmodifiableList(this.menus);
    }

    public Map<String, Runnable> getRoutes() {
        return Collections.unmodifiableMap(this.routes);
    }
}
