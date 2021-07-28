package com.example.openfeign.controller;

import com.example.openfeign.client.ApiClient;
import com.example.openfeign.client.ApiClientException;
import com.example.openfeign.model.ApiExtendedStatus;
import com.example.openfeign.model.ApiStatusBase;
import lombok.RequiredArgsConstructor;
import com.example.openfeign.model.ApiStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.function.Supplier;

@Controller("/client")
@RequiredArgsConstructor
@Slf4j
public class AppController {
    private final ApiClient apiClient;
    @GetMapping(value="/status")
    ResponseEntity<String> getApiStatus() {
        ResponseEntity<? extends ApiStatus> statusResponse = apiClient.getSimpleStatus();
        //ResponseEntity<ApiStatus> statusResponse = new ResponseEntity<>(new ApiStatus("SUCCESS", "OK", "Complete"), HttpStatus.OK);
        ApiStatus status = statusResponse.getBody();
        return new ResponseEntity<>(String.format("API status: [%s] code: [%s] reason: [%s]",
                status.getStatus(), status.getCode(), status.getReason()),
                statusResponse.getStatusCode());
    }
    @GetMapping(value="/status401")
    ResponseEntity<String> get401Status() {
        return getResponseEntityForSupplier(() -> apiClient.getAnyStatus(401));
    }

    @GetMapping(value="/status404")
    ResponseEntity<String> get404Status() {
        return getResponseEntity(apiClient.getAnyStatus(404));
    }
    @GetMapping(value="/status500")
    ResponseEntity<String> get500Status() {
        return getResponseEntity(apiClient.getAnyStatus(500));
    }
    @GetMapping(value="/status501")
    ResponseEntity<String> get501Status() {
        return getResponseEntity(apiClient.getAnyStatus(501));
    }
    @GetMapping(value="/status502")
    ResponseEntity<String> get502Status() {
        return getResponseEntity(apiClient.getAnyStatus(502));
    }
    @GetMapping(value="/status503")
    ResponseEntity<String> get503Status() {
        return getResponseEntity(apiClient.getAnyStatus(503));
    }

    private ResponseEntity<String> getResponseEntity(ResponseEntity<ApiStatus> statusResponse) {
        ApiStatus status = statusResponse.getBody();
        if (status.getCode().equals(ApiStatus.SUCCESS)) {
            ApiExtendedStatus extendedStatus = (ApiExtendedStatus) status;
            return new ResponseEntity<>(String.format("API status: [%s] code: [%s] reason: [%s] title: [%s] text: [%s]",
                    status.getStatus(), status.getCode(), status.getReason(), extendedStatus.getTitle(), extendedStatus.getText()),
                    statusResponse.getStatusCode());
        }
        return new ResponseEntity<>(String.format("API status: [%s] code: [%s] reason: [%s]",
                status.getStatus(), status.getCode(), status.getReason()),
                statusResponse.getStatusCode());
    }

    private ResponseEntity<String> getResponseEntityForSupplier(Supplier<ResponseEntity<ApiStatus>> supplier) {
        ResponseEntity<ApiStatus> statusResponse = null;
        try {
            statusResponse = supplier.get();
        } catch (Exception e) {
            log.error("Supplier exception.", e);
        }
        ApiStatus status = statusResponse.getBody();
        if (status.getCode().equals(ApiStatus.SUCCESS)) {
            ApiExtendedStatus extendedStatus = (ApiExtendedStatus) status;
            return new ResponseEntity<>(String.format("API status: [%s] code: [%s] reason: [%s] title: [%s] text: [%s]",
                    status.getStatus(), status.getCode(), status.getReason(), extendedStatus.getTitle(), extendedStatus.getText()),
                    statusResponse.getStatusCode());
        }
        return new ResponseEntity<>(String.format("API status: [%s] code: [%s] reason: [%s]",
                status.getStatus(), status.getCode(), status.getReason()),
                statusResponse.getStatusCode());
    }
}
