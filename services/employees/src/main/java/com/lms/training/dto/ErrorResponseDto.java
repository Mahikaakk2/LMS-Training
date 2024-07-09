package com.lms.training.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {
    private String errorMessage;
    private String apiPath;
    private LocalDateTime errorTime;
    private HttpStatus statusCode;
}
