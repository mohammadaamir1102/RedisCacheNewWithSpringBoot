package com.amir.redis.exception;

import com.amir.redis.util.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PerformanceReportException.class)
    public ResponseEntity<?> performanceReportExceptionHandler(PerformanceReportException performanceReportException) {
        ErrorResponse failure = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), performanceReportException.getErrorMessage());
        return new ResponseEntity<>(failure, HttpStatus.BAD_REQUEST);
    }
}
