package com.shivam.cabbooking.exception;

public class NoCabsAvailableException extends RuntimeException {
    private static final String NO_CABS_AVAILABLE_MSG = "No cabs are available in the given area";

    public NoCabsAvailableException() {
        super(NO_CABS_AVAILABLE_MSG);
    }
}
