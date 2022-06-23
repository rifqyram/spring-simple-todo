package com.enigma.simpletodo.utils;

import org.springframework.http.HttpStatus;

public class WebResponse<T> {

    private final int code;

    private final HttpStatus status;

    private final String message;

    private final T data;

    public WebResponse(int code, HttpStatus status, String message, T data) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
