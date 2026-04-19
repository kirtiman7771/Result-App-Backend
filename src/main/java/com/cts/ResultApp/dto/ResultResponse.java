package com.cts.ResultApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultResponse {
    private String studentFullName;
    private String courseName;
    private String courseCode;
    private Integer marksObtained;
    private String grade;
}
