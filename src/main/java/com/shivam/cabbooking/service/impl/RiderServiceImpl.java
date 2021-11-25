package com.shivam.cabbooking.service.impl;

import com.shivam.cabbooking.exception.ObjectAlreadyExists;
import com.shivam.cabbooking.model.Rider;
import com.shivam.cabbooking.service.RiderService;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.NonNull;

public class RiderServiceImpl implements RiderService {

    Map<String, Rider> riderStore;

    public RiderServiceImpl() {
        riderStore = new HashMap<>();
    }

    @Override
    public Rider createRider(@NonNull Rider newRider) {
        if(riderStore.containsKey(newRider.getId())){
            throw new ObjectAlreadyExists(ObjectAlreadyExists.RIDER_ALREADY_EXISTS_MSG);
        }
        return riderStore.put(newRider.getId(), newRider);
    }

    @Override
    public Optional<Rider> getRider(@NonNull String riderId) {
        if(riderStore.containsKey(riderId)){
            return Optional.of(riderStore.get(riderId));
        }
        return Optional.empty();
    }
}
