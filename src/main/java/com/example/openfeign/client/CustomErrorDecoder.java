package com.example.openfeign.client;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        String requestUrl = response.request().url();
        Response.Body responseBody = response.body();
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());

        if (responseStatus.is5xxServerError()) {
            return new ApiClientException(requestUrl, responseStatus);
        } else if (responseStatus.is4xxClientError()) {
            return new ApiClientException(requestUrl, responseStatus);
        } else {
            return new Exception(String.format("Unknown error. Http status %d.", response.status()));
        }
    }
}
