package com.suu.hppa.summer_training.presentation.cli.viewmodel;

public record Menu(String keyAlphabet, String name, Runnable renderer) {
    @Override
    public String toString() {
        return this.keyAlphabet + ": " + this.name;
    }
}

