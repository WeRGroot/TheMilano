package com.shivam.cabbooking.exception;

public class InvalidCabOperation extends RuntimeException {
    private static final String INVALID_CAB_OPERATION = "Invalid cab operation";

    public InvalidCabOperation() {
        super(INVALID_CAB_OPERATION);
    }
}
