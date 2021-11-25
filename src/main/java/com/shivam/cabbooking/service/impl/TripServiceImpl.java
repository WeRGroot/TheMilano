package com.shivam.cabbooking.service.impl;

import com.shivam.cabbooking.exception.CabNotFoundException;
import com.shivam.cabbooking.exception.NoCabsAvailableException;
import com.shivam.cabbooking.model.Cab;
import com.shivam.cabbooking.model.CabStatus;
import com.shivam.cabbooking.model.Location;
import com.shivam.cabbooking.model.Rider;
import com.shivam.cabbooking.model.Trip;
import com.shivam.cabbooking.service.CabService;
import com.shivam.cabbooking.service.RiderService;
import com.shivam.cabbooking.service.TripService;
import com.shivam.cabbooking.strategy.CabMatchingStrategy;
import com.shivam.cabbooking.strategy.FareChargingStrategy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.NonNull;

public class TripServiceImpl implements TripService {

    public static final Double MAX_ALLOWED_TRIP_MATCHING_DISTANCE = 20.0;

    private Map<String, ArrayList<Trip>> tripStore;

    private CabService cabService;
    private RiderService riderService;
    private CabMatchingStrategy cabMatchingStrategy;
    private FareChargingStrategy fareChargingStrategy;

    public TripServiceImpl(CabService cabService,
        RiderService riderService,
        CabMatchingStrategy cabMatchingStrategy,
        FareChargingStrategy fareChargingStrategy) {
        this.cabService = cabService;
        this.riderService = riderService;
        this.cabMatchingStrategy = cabMatchingStrategy;
        this.fareChargingStrategy = fareChargingStrategy;
        this.tripStore = new HashMap<>();
    }

    @Override
    public Cab createTrip(@NonNull Rider rider, @NonNull Location fromPoint,
        @NonNull Location toPoint) {
        List<Cab> closeByAvailableCabs = cabService.getCabs(fromPoint,
                MAX_ALLOWED_TRIP_MATCHING_DISTANCE)
            .stream()
            .filter(cab -> cab.getStatus() == CabStatus.STAND_BY)
            .collect(Collectors.toList());

        Optional<Cab> selectedCab = cabMatchingStrategy.match(rider, closeByAvailableCabs, fromPoint, toPoint);
        if(!selectedCab.isPresent()){
            throw new NoCabsAvailableException();
        }

        double estimateCharges = fareChargingStrategy.calculateFare(fromPoint, toPoint);
        Trip trip = new Trip(rider, selectedCab.get(), estimateCharges, fromPoint, toPoint);
        ArrayList<Trip> ridersTrips = tripStore.getOrDefault(rider.getId(), new ArrayList<>());
        ridersTrips.add(trip);
        tripStore.put(rider.getId(), ridersTrips);
        selectedCab.get().setCurrentTrip(trip);
        return selectedCab.get();
    }

    @Override
    public List<Trip> tripHistory(@NonNull Rider rider) {
        return tripStore.getOrDefault(rider.getId(), new ArrayList<>());
    }

    @Override
    public void startTrip(@NonNull String cabId) {
        Optional<Cab> cabOptional = cabService.getCab(cabId);
        if(!cabOptional.isPresent()){
            throw new CabNotFoundException();
        }
        cabOptional.get().startTrip();
    }

    @Override
    public double endTrip(@NonNull String cabId) {
        Optional<Cab> cabOptional = cabService.getCab(cabId);
        if(!cabOptional.isPresent()){
            throw new CabNotFoundException();
        }
        return cabOptional.get().endTrip();
    }

}
