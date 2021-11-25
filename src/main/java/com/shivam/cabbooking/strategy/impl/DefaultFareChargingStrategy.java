package com.shivam.cabbooking.strategy.impl;

import com.shivam.cabbooking.model.Location;
import com.shivam.cabbooking.strategy.FareChargingStrategy;
import lombok.NonNull;

public class DefaultFareChargingStrategy implements FareChargingStrategy {

    private static final double FARE_PER_KM = 12.0;

    @Override
    public double calculateFare(@NonNull Location fromLocation, @NonNull Location toLocation) {
        return fromLocation.distance(toLocation) * FARE_PER_KM;
    }
}
