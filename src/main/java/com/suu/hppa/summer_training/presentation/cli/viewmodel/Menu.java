package com.suu.hppa.summer_training.presentation.cli.viewmodel;

public record Menu(String keyAlphabet, String name) {
    public String getLabel() {
        return this.keyAlphabet + ": " + this.name;
    }
}

