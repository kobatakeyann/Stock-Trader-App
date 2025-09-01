package com.suu.hppa.stocktradarapp.presentation.cli.helper.validation;

public class ValidationResult {
    private final String value;
    private final ValidationStatus status;
    private final String errorMessage;

    private ValidationResult(String value, ValidationStatus status, String errorMessage) {
        this.value = value;
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public static ValidationResult ok(String value) {
        return new ValidationResult(value, ValidationStatus.OK, null);
    }

    public static ValidationResult error(String value, String errorMessage) {
        return new ValidationResult(value, ValidationStatus.ERROR, errorMessage);
    }

    public String value() {
        return this.value;
    }

    public ValidationStatus status() {
        return this.status;
    }

    public String errorMessage() {
        if (this.errorMessage == null) {
            return "There is no error message.";
        }
        return this.errorMessage;
    }
}


