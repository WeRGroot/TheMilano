package com.shivam.cabbooking.model;

import com.shivam.cabbooking.exception.InvalidCabOperation;
import com.shivam.cabbooking.exception.NoTripFoundException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cab {
    private String id;
    private String driverName;

    private Location currentLocation;
    private boolean isAvailable;

    private Trip currentTrip;

    private CabStatus status;

    public Cab(String id, String driverName) {
        this.id = id;
        this.driverName = driverName;
        status = CabStatus.STAND_BY;
    }

    public double endTrip(){
        if(currentTrip == null){
            throw new NoTripFoundException();
        }
        double charges = currentTrip.endTrip();
        currentTrip = null;
        status = CabStatus.STAND_BY;
        return charges;
    }

    public void startTrip() {
        if(currentTrip == null){
            throw new NoTripFoundException();
        }
        if(status != CabStatus.STAND_BY){
            throw new InvalidCabOperation();
        }
        status = CabStatus.IN_TRIP;
        currentTrip.setTripStatus(TripStatus.IN_PROGRESS);
    }

    @Override
    public String toString() {
        return "Cab{" +
            "id='" + id + '\'' +
            ", driverName='" + driverName + '\'' +
            '}';
    }
}
