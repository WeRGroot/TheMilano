package com.shivam.cabbooking.service;

import com.shivam.cabbooking.model.Cab;
import com.shivam.cabbooking.model.Location;
import com.shivam.cabbooking.model.Rider;
import com.shivam.cabbooking.model.Trip;
import java.util.List;
import lombok.NonNull;

public interface TripService {
    Cab createTrip(@NonNull Rider rider, @NonNull Location fromPoint, @NonNull Location toPoint);
    List<Trip> tripHistory(@NonNull final Rider rider);
    void startTrip(@NonNull String cabId);
    double endTrip(@NonNull String cabId);
}
