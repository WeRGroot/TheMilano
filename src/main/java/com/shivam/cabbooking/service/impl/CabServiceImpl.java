package com.shivam.cabbooking.service.impl;

import com.shivam.cabbooking.exception.CabNotFoundException;
import com.shivam.cabbooking.exception.ObjectAlreadyExists;
import com.shivam.cabbooking.model.Cab;
import com.shivam.cabbooking.model.Location;
import com.shivam.cabbooking.service.CabService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.NonNull;

public class CabServiceImpl implements CabService {

    private Map<String, Cab> cabStore;

    public CabServiceImpl() {
        this.cabStore = new HashMap<>();
    }

    @Override
    public Cab createCab(@NonNull Cab cab) {
        if(cabStore.containsKey(cab.getId())){
            throw new ObjectAlreadyExists(ObjectAlreadyExists.CAB_ALREADY_EXISTS_MSG);
        }

        return cabStore.put(cab.getId(), cab);
    }

    @Override
    public Optional<Cab> getCab(@NonNull String cabId) {
        if(!cabStore.containsKey(cabId)){
            return Optional.empty();
        }
        return Optional.of(cabStore.get(cabId));
    }

    @Override
    public void updateCabLocation(@NonNull String cabId, @NonNull Location location) {
        if(!cabStore.containsKey(cabId)){
            throw new CabNotFoundException();
        }
        cabStore.get(cabId).setCurrentLocation(location);
    }

    @Override
    public void updateCabAvailability(@NonNull String cabId,
        boolean newAvailability) {
        if(!cabStore.containsKey(cabId)){
            throw new CabNotFoundException();
        }
        cabStore.get(cabId).setAvailable(newAvailability);
    }

    @Override
    public List<Cab> getCabs(@NonNull Location fromPoint, double distance) {
        List<Cab> result = new ArrayList<>();
        for(Cab cab : cabStore.values()){
            if(cab.isAvailable() && cab.getCurrentLocation().distance(fromPoint) <= distance){
                result.add(cab);
            }
        }

        return result;
    }
}
