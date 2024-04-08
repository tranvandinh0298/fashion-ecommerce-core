package com.source.dinhtv.fashionecommercecore.http.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class ResponseTemplate<T> extends ResponseEntity<T> {
    private String resultCode;
    private String resultMessage;
    private T data;

    public ResponseTemplate(String resultCode, String resultMessage, T data) {
        super(HttpStatus.OK);
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.data = data;
    }

    public ResponseTemplate(String resultCode, String resultMessage, T data, HttpStatusCode status) {
        super(status);
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.data = data;
    }

    public ResponseTemplate(T body, HttpStatusCode status, String resultCode, String resultMessage, T data) {
        super(body, status);
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.data = data;
    }

    public ResponseTemplate(MultiValueMap<String, String> headers, HttpStatusCode status, String resultCode, String resultMessage, T data) {
        super(headers, status);
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.data = data;
    }

    public ResponseTemplate(T body, MultiValueMap<String, String> headers, int rawStatus, String resultCode, String resultMessage, T data) {
        super(body, headers, rawStatus);
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.data = data;
    }

    public ResponseTemplate(T body, MultiValueMap<String, String> headers, HttpStatusCode statusCode, String resultCode, String resultMessage, T data) {
        super(body, headers, statusCode);
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.data = data;
    }
}
