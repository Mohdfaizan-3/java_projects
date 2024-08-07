package com.lg.electronic_store.utils.apiResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private boolean success;
    private HttpStatus status;
}
