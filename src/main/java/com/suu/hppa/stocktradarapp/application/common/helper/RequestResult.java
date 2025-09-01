package com.suu.hppa.stocktradarapp.application.common.helper;

public class RequestResult {
    private final String message;
    private final Object data;
    private final RequestStatus status;

    private RequestResult(RequestStatus status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static RequestResult success(Object data) {
        return new RequestResult(RequestStatus.SUCCESS, null, data);
    }

    public static RequestResult fail(String message) {
        return new RequestResult(RequestStatus.FAILURE, message, null);
    }

    public String message() {
        return message;
    }

    public RequestStatus status() {
        return status;
    }

    public Object data() {
        if (status != RequestStatus.SUCCESS) {
            throw new IllegalStateException("No data available for failed requests.");
        }
        return data;
    }

}

