package com.example.openfeign.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class ApiExtendedStatus extends ApiStatusBase {
    private String title;
    private String text;
}
