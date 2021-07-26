package com.example.openfeign.controller;

import com.example.openfeign.OpenfeignApplication;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import com.example.openfeign.model.ApiStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/client")
@RequiredArgsConstructor
@Slf4j
public class AppController {
    private final OpenfeignApplication.ApiClient apiClient;
    @GetMapping(value="/status")
    ResponseEntity<String> getApiStatus() {
        ResponseEntity<ApiStatus> statusResponse = apiClient.getSimpleStatus();
        //ResponseEntity<ApiStatus> statusResponse = new ResponseEntity<>(new ApiStatus("SUCCESS", "OK", "Complete"), HttpStatus.OK);
        ApiStatus status = statusResponse.getBody();
        return new ResponseEntity<>(String.format("API status: [%s] code: [%s] reason: [%s]",
                status.getStatus(), status.getCode(), status.getReason()),
                statusResponse.getStatusCode());
    }
    @GetMapping(value="/status404")
    ResponseEntity<String> get404Status() {
        ResponseEntity<ApiStatus> statusResponse = null;
        try {
            statusResponse = apiClient.getNotFoundStatus();
        } catch (FeignException.FeignClientException e) {
            statusResponse = new ResponseEntity<>(new ApiStatus("ERROR", "Exception", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //ResponseEntity<ApiStatus> statusResponse = new ResponseEntity<>(new ApiStatus("SUCCESS", "OK", "Complete"), HttpStatus.OK);
        ApiStatus status = statusResponse.getBody();
        log.info(String.format("Http Status: %d", statusResponse.getStatusCodeValue()));
        return new ResponseEntity<>(String.format("API status: [%s] code: [%s] reason: [%s]",
                status.getStatus(), status.getCode(), status.getReason()),
                statusResponse.getStatusCode());
    }
}
