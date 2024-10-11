package com.yash.onlineHomeDecor.exception;



public class OrderException extends Exception {
    public OrderException(String message) {
        super(message);
    }

    public OrderException(String message, Throwable cause) {
        super(message, cause);
    }
}

 public class OrderNotFoundException extends OrderException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
