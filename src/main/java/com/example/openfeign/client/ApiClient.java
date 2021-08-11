package com.example.openfeign.client;

import com.example.openfeign.model.ApiStatus;
import com.example.openfeign.model.ApiStatusBase;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "simple", url = "http://localhost:8090")
public interface ApiClient {
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<ApiStatusBase> getSimpleStatus();

    @RequestMapping(value="/", method = RequestMethod.GET)
    ResponseEntity<ApiStatusBase> getAnyStatus(@RequestParam(value = "status") Integer status);

    @RequestMapping(value="/delay", method = RequestMethod.GET)
    ResponseEntity<ApiStatusBase> getSuccessWithDelay(@RequestParam(value="delay") Integer delay);
}
