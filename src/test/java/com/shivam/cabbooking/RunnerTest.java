package com.shivam.cabbooking;

import com.shivam.cabbooking.model.Cab;
import com.shivam.cabbooking.model.Location;
import com.shivam.cabbooking.model.Rider;
import com.shivam.cabbooking.service.CabService;
import com.shivam.cabbooking.service.RiderService;
import com.shivam.cabbooking.service.TripService;
import com.shivam.cabbooking.service.impl.CabServiceImpl;
import com.shivam.cabbooking.service.impl.RiderServiceImpl;
import com.shivam.cabbooking.service.impl.TripServiceImpl;
import com.shivam.cabbooking.strategy.impl.DefaultCabMatchingStrategy;
import com.shivam.cabbooking.strategy.impl.DefaultFareChargingStrategy;
import org.junit.jupiter.api.Test;

public class RunnerTest {
    private CabService cabService;
    private RiderService riderService;
    private TripService tripService;

    public RunnerTest() {
        cabService = new CabServiceImpl();
        riderService = new RiderServiceImpl();
        tripService = new TripServiceImpl(
            cabService,
            riderService,
            new DefaultCabMatchingStrategy(),
            new DefaultFareChargingStrategy());
    }

    @Test
    void testHappyPath() {
        Rider ram = new Rider("rider1", "Ram");
        Rider ramesh = new Rider("rider2", "Ramesh");
        Rider vishal = new Rider("rider3", "Vishal");

        Cab alicesCab = new Cab("cab1", "Alice");
        Cab bobsCab = new Cab("cab2", "Bob");
        Cab jacksCab = new Cab("cab3", "Jack");

        cabService.createCab(alicesCab);
        cabService.createCab(bobsCab);
        cabService.createCab(jacksCab);

        cabService.updateCabLocation(alicesCab.getId(), new Location(1.0, 2.0));
        cabService.updateCabLocation(bobsCab.getId(), new Location(50.0, 70.0));
        cabService.updateCabLocation(jacksCab.getId(), new Location(0.5, 2.5));

        alicesCab.setAvailable(true);
        bobsCab.setAvailable(true);
        jacksCab.setAvailable(true);

        System.out.println(tripService.tripHistory(ram));
        System.out.println(tripService.tripHistory(ramesh));
        System.out.println(tripService.tripHistory(vishal));

        Cab cab = tripService.createTrip(ram, new Location(1.0, 1.0), new Location(10.0, 10.0));
        System.out.println(tripService.tripHistory(ram));

        tripService.startTrip(cab.getId());

        System.out.println(tripService.tripHistory(ram));
    }
}
