package com.shivam.cabbooking.service;

import com.shivam.cabbooking.model.Rider;
import java.util.Optional;
import lombok.NonNull;

public interface RiderService {
    Rider createRider(@NonNull final Rider newRider);
    Optional<Rider> getRider(@NonNull final  String riderId);
}
