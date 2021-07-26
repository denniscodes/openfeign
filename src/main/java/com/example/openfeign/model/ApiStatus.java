package com.example.openfeign.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiStatus {
    private String status;
    private String code;
    private String reason;
    public ApiStatus(String status, String code, String reason) {
        this.status = status;
        this.code = code;
        this.reason = reason;
    }
}
