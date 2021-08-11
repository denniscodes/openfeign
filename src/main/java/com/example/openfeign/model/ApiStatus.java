package com.example.openfeign.model;

public interface ApiStatus {
    String ERROR = "ERROR";
    String SUCCESS = "SUCCESS";

    String getStatus();
    void setStatus(String status);

    String getCode();
    void setCode(String code);

    String getReason();
    void setReason(String reason);
}
