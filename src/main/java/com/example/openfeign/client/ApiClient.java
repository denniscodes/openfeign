package com.example.openfeign.client;

import com.example.openfeign.model.ApiStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "simple", url = "http://localhost:8090")
public interface ApiClient {
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<? extends ApiStatus> getSimpleStatus();

    @RequestMapping(value="/", method = RequestMethod.GET)
    ResponseEntity<ApiStatus> getAnyStatus(@RequestParam(value = "status") Integer status);

}
