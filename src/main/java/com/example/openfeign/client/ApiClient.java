package com.example.openfeign.client;

import com.example.openfeign.model.ApiStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "simple", url = "http://localhost:8090")
public interface ApiClient {
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<ApiStatus> getSimpleStatus();

    @RequestMapping(value = "status401", method = RequestMethod.GET)
    ResponseEntity<ApiStatus> getNotAuthorizedStatus();

    @RequestMapping(value = "status404", method = RequestMethod.GET)
    ResponseEntity<ApiStatus> getNotFoundStatus();

    @RequestMapping(value = "status501", method = RequestMethod.GET)
    ResponseEntity<ApiStatus> getServerErrorStatus();
}
