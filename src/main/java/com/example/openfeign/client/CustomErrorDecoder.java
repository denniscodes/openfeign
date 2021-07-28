package com.example.openfeign.client;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        log.info("Decoding error.");
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
