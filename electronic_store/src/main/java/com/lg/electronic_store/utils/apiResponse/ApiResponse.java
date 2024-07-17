package com.lg.electronic_store.utils.apiResponse;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiResponse {
    private String message;
    private boolean success;
    private HttpStatus status;
}
