package com.example.openfeign;

import com.example.openfeign.model.ApiStatus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@SpringBootApplication
@EnableFeignClients
public class OpenfeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenfeignApplication.class, args);
    }

    @FeignClient(value = "simple", url="http://localhost:8090")
    public static interface ApiClient {
        @RequestMapping(method = RequestMethod.GET)
        ResponseEntity<ApiStatus> getSimpleStatus();
        @RequestMapping(value="status404", method = RequestMethod.GET)
        ResponseEntity<ApiStatus> getNotFoundStatus();
    }
}
