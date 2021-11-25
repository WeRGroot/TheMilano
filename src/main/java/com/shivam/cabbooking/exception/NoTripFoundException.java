package com.shivam.cabbooking.exception;

public class NoTripFoundException extends RuntimeException {
    private static final String NO_TRIP_FOUND_MSG = "No trip found for the given cab";

    public NoTripFoundException() {
        super(NO_TRIP_FOUND_MSG);
    }
}
