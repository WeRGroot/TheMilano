package com.shivam.cabbooking.service;

import com.shivam.cabbooking.model.Cab;
import com.shivam.cabbooking.model.Location;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;

public interface CabService {

    Cab createCab(@NonNull final Cab cab);
    Optional<Cab> getCab(@NonNull final String cabId);
    void updateCabLocation(@NonNull final String cabId, @NonNull final Location location);
    void updateCabAvailability(@NonNull final String cabId, @NonNull final boolean newAvailability);

    List<Cab> getCabs(@NonNull final Location fromPoint, @NonNull final double distance);
}
