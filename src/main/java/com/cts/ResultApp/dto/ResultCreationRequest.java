package com.cts.ResultApp.dto;

import lombok.Data;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
public class ResultCreationRequest {
    @NotNull(message = "Student ID cannot be null")
    private Long studentId;

    @NotNull(message = "Course ID cannot be null")
    private Long courseId;

    @NotNull(message = "Marks obtained cannot be null")
    @Min(value = 0, message = "Marks must be 0 or greater")
    @Max(value = 100, message = "Marks must be 100 or less")
    private Integer marksObtained;
}
