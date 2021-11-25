package com.shivam.cabbooking.exception;

public class ObjectAlreadyExists extends RuntimeException{
    public static final String CAB_ALREADY_EXISTS_MSG =
        "Cab with given id is already present in the store";

    public static final String RIDER_ALREADY_EXISTS_MSG =
        "Cab with given id is already present in the store";

    public ObjectAlreadyExists(String message) {
        super(message);
    }
}
