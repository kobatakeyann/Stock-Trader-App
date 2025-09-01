package com.suu.hppa.stocktradarapp.presentation.cli.view;

import org.springframework.stereotype.Component;

@Component
public class ApplicationExitView {
    public void render() {
        System.out.println("アプリケーションを終了します。");
        System.exit(0);
    }
}
