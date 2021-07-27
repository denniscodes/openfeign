package com.example.openfeign;

import com.example.openfeign.model.ApiStatus;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FeignException.class)
    @ResponseBody
    public ResponseEntity<?> handleFeignStatusException(FeignException e, HttpServletResponse response) {
        response.setStatus(e.status());
        return new ResponseEntity<ApiStatus>(
                new ApiStatus(ApiStatus.ERROR, Integer.toString(response.getStatus()), e.getMessage()),
                HttpStatus.valueOf(response.getStatus()));
    }

}
