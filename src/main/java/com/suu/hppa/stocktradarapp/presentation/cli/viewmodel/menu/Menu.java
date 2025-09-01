package com.suu.hppa.stocktradarapp.presentation.cli.viewmodel.menu;

public record Menu(String keyAlphabet, String name) {
    public String getLabel() {
        return this.keyAlphabet + ": " + this.name;
    }
}

