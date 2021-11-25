package com.shivam.cabbooking.model;

import java.text.DecimalFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class Trip {
    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private Rider rider;
    private Cab cab;
    private double charges;
    private Location fromLocation;
    private Location toLocation;
    private TripStatus tripStatus;

    public Trip(Rider rider, Cab cab, double charges, Location fromLocation,
        Location toLocation) {
        this.rider = rider;
        this.cab = cab;
        this.charges = Double.valueOf(decimalFormat.format(charges));
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.tripStatus = TripStatus.READY;
    }

    public void startTrip(){
        tripStatus = TripStatus.IN_PROGRESS;
    }

    public double endTrip(){
        tripStatus = TripStatus.FINISHED;
        return getCharges();
    }

    @Override
    public String toString() {
        return "Trip{" +
            "cab=" + cab +
            ", charges=" + charges +
            ", fromLocation=" + fromLocation +
            ", toLocation=" + toLocation +
            ", tripStatus=" + tripStatus +
            '}';
    }
}
