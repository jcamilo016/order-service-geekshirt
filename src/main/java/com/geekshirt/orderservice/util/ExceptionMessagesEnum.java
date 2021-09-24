package com.geekshirt.orderservice.util;

public enum ExceptionMessagesEnum {
    INCORRECT_REQUEST("Empty or incorrect parameters were provided in the order request");
    private final String value;

    ExceptionMessagesEnum(String message) {
        value = message;
    }

    public String getValue() {
        return value;
    }


}
