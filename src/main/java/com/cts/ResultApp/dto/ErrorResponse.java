package com.cts.ResultApp.dto;

import java.time.LocalDateTime;

/**
 * Standard DTO used to return structured error messages for all API exceptions.
 */
public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path
) {
    // Records are immutable and concise (Java 16+)
    // The canonical constructor is automatically generated.
}
