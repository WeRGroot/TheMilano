package com.shivam.cabbooking.strategy;

import com.shivam.cabbooking.model.Cab;
import com.shivam.cabbooking.model.Location;
import com.shivam.cabbooking.model.Rider;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;

public interface CabMatchingStrategy {

    Optional<Cab> match(
        @NonNull Rider rider,
        @NonNull List<Cab> cabList,
        @NonNull Location fromPoint,
        @NonNull Location toPoint);
}
