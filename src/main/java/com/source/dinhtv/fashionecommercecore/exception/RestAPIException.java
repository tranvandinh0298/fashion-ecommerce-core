package com.source.dinhtv.fashionecommercecore.exception;

public class RestAPIException extends RuntimeException  {
    public RestAPIException(String message) {
        super(message);
    }

    public RestAPIException(String message, Throwable cause) {
        super(message, cause);
    }
}
