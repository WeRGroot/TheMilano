package com.shivam.cabbooking.exception;

public class CabNotFoundException extends RuntimeException {
    private static final String CAB_NOT_FOUND_MSG = "Given cab is not found in the store";

    public CabNotFoundException() {
        super(CAB_NOT_FOUND_MSG);
    }
}
