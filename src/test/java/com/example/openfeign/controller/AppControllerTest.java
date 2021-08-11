package com.example.openfeign.controller;

import com.example.openfeign.client.ApiClient;
import com.example.openfeign.model.ApiStatus;
import com.example.openfeign.model.ApiStatusBase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AppControllerTest {
    @Mock
    private ApiClient apiClient;
    @InjectMocks
    private AppController controller;

    @Test
    void status200Default() {
        ResponseEntity<ApiStatusBase> apiResponse = new ResponseEntity<>(new ApiStatusBase(ApiStatus.SUCCESS, "", ""),
                HttpStatus.OK);
        when(apiClient.getSimpleStatus()).thenReturn(apiResponse);
        ResponseEntity<String> controllerResponse = controller.getApiStatus();
        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
    }
}
