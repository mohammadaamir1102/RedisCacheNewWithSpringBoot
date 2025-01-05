package com.amir.redis.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class PerformanceReportException extends RuntimeException{
    private String errorMessage;
}
