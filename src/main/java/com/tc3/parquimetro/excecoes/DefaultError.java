package com.tc3.parquimetro.excecoes;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter @Setter
public class DefaultError {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public DefaultError() {}

    public DefaultError(Instant timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}
