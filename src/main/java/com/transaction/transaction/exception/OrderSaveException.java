package com.transaction.transaction.exception;

public class OrderSaveException extends RuntimeException {
    public OrderSaveException(String message) {
        super(message);
    }
}