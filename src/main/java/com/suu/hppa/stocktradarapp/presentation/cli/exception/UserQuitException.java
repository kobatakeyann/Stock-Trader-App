package com.suu.hppa.stocktradarapp.presentation.cli.exception;

public class UserQuitException extends RuntimeException {
    public UserQuitException(String message) {
        super(message);
    }
}
