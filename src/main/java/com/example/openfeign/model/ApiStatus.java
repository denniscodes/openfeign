package com.example.openfeign.model;

public interface ApiStatus {
    String ERROR = "ERROR";
    String SUCCESS = "SUCCESS";

    String getStatus();

    String getCode();

    String getReason();
}
