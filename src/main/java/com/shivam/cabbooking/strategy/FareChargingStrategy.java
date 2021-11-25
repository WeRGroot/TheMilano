package com.shivam.cabbooking.strategy;

import com.shivam.cabbooking.model.Location;
import lombok.NonNull;

public interface FareChargingStrategy {

    double calculateFare(@NonNull Location fromLocation, @NonNull Location toLocation);
}
