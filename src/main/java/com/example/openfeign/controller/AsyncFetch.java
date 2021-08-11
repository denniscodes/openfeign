package com.example.openfeign.controller;

import com.example.openfeign.client.ApiClient;
import com.example.openfeign.model.ApiStatusBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
public class AsyncFetch {
    public Duration runTwoRequsts(ApiClient client) throws ExecutionException, InterruptedException {
        Instant start = Instant.now();
        CompletableFuture<ResponseEntity<ApiStatusBase>> longRunning = getLongRunningStatus(client);
        CompletableFuture<ResponseEntity<ApiStatusBase>> quickRunning = getQuickStatus(client);
        CompletableFuture.allOf(longRunning, quickRunning);
        Instant end = Instant.now();
        ResponseEntity<ApiStatusBase> longResult = longRunning.get();
        ResponseEntity<ApiStatusBase> quickResult = quickRunning.get();
        log.info("Long result: {}, Quick result: {}", longResult.getStatusCodeValue(), quickResult.getStatusCodeValue());
        return Duration.between(start, end);
    }

    @Async public CompletableFuture<ResponseEntity<ApiStatusBase>> getLongRunningStatus(ApiClient client) {
        log.info("Invoking delay.");
        return CompletableFuture.supplyAsync(()->client.getSuccessWithDelay(9));
    }
    @Async public CompletableFuture<ResponseEntity<ApiStatusBase>> getQuickStatus(ApiClient client) {
        log.info("Invoking immediate.");
        return CompletableFuture.supplyAsync(()->client.getSimpleStatus());
    }
}
