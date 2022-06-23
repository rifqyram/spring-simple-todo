package com.enigma.simpletodo.utils;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

public class WebResponse<T> {

    @Schema(example = "200")
    private final int code;

    @Schema(example = "OK")
    private final HttpStatus status;

    @Schema(example = "Success")
    private final String message;

    @Schema(example = "Any")
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
