package com.example.openfeign;

import com.example.openfeign.model.ApiStatus;
import com.example.openfeign.model.ApiStatusBase;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> handleFeignStatusException(Exception e, HttpServletResponse response) {
        log.error("Generic exception.", e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(FeignException.class)
    @ResponseBody
    public ResponseEntity<?> handleFeignStatusException(FeignException e, HttpServletResponse response) {
        log.error("FeignException.", e);
        response.setStatus(e.status());
        return new ResponseEntity<ApiStatusBase>(
                new ApiStatusBase(ApiStatus.ERROR, Integer.toString(response.getStatus()), e.getMessage()),
                HttpStatus.valueOf(response.getStatus()));
    }

}
