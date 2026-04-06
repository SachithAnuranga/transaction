package com.transaction.transaction.exception;

import com.transaction.transaction.response.StandardResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderSaveException.class)
    public ResponseEntity<StandardResponse> handleOrderSaveException(
            OrderSaveException ex) {

        StandardResponse response = new StandardResponse(
                500,
                null,
                ex.getMessage()
        );

        return ResponseEntity.status(500).body(response);
    }
}
