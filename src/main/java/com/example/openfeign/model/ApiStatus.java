package com.example.openfeign.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiStatus {
    public static final String ERROR = "ERROR";
    public static final String SUCCESS = "SUCCESS";

    private String status;
    private String code;
    private String reason;
    public ApiStatus(String status, String code, String reason) {
        this.status = status;
        this.code = code;
        this.reason = reason;
    }
}
