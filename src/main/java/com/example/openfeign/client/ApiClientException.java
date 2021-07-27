package com.example.openfeign.client;

import feign.Response;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiClientException extends RuntimeException {
    private String requestUrl;
    private HttpStatus responseStatus;

    public ApiClientException(String requestUrl, HttpStatus responseStatus) {
        this.requestUrl = requestUrl;
        this.responseStatus = responseStatus;
    }
}
