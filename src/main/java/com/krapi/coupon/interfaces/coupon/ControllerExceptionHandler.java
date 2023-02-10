package com.krapi.coupon.interfaces.coupon;

import com.krapi.coupon.application.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handleDefaultExceptions(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDto(ErrorCode.of(e.getMessage())));
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<?> handleRuntimeExceptions(RuntimeException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDto(ErrorCode.of(e.getMessage())));
    }
}
