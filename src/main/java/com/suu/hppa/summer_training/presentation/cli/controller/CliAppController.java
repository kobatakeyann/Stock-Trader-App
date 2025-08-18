package com.suu.hppa.summer_training.presentation.cli.controller;

import org.springframework.stereotype.Component;
import com.suu.hppa.summer_training.presentation.cli.view.HomeMenuView;

@Component
public class CliAppController {
    private final HomeMenuView homeMenuView;

    public CliAppController(HomeMenuView homeMenuView) {
        this.homeMenuView = homeMenuView;
    }

    public void start() {
        System.out.println("株式取引管理システムを開始します。");
        this.homeMenuView.render();
    }
}
