package com.wilddev.geo.exceptions;

public class HttpRequestFailedException extends Exception {

    public HttpRequestFailedException(String message) {
        super(message);
    }

    public HttpRequestFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
