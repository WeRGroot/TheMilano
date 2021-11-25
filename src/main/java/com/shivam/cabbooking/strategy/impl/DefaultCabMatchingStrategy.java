package com.shivam.cabbooking.strategy.impl;

import com.shivam.cabbooking.model.Cab;
import com.shivam.cabbooking.model.Location;
import com.shivam.cabbooking.model.Rider;
import com.shivam.cabbooking.strategy.CabMatchingStrategy;
import java.util.List;
import java.util.Optional;
import lombok.NonNull;

public class DefaultCabMatchingStrategy implements CabMatchingStrategy {

    @Override
    public Optional<Cab> match(@NonNull Rider rider, @NonNull List<Cab> cabList, @NonNull Location fromPoint,
        @NonNull Location toPoint) {
        if(cabList.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(cabList.get(0));
    }
}
