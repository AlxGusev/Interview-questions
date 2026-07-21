package oop_desing.parcking_lot.domain.parking;

import oop_desing.parcking_lot.parking_strategy.SpotAssignmentStrategy;
import oop_desing.parcking_lot.pricing_strategy.PricingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingLotBuilder {
    private final List<ParkingFloor> floors = new ArrayList<>();
    private int spotCounter = 1;

    public ParkingLotBuilder addFloor(int floorNumber, Map<SpotSize, Integer> spotsBySize) {
        List<ParkingSpot> spots = new ArrayList<>();
        for (var entry : spotsBySize.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                spots.add(new ParkingSpot(spotCounter++, entry.getKey()));
            }
        }
        floors.add(new ParkingFloor(floorNumber, spots));
        return this;
    }

    public ParkingLot build(SpotAssignmentStrategy assignmentStrategy, PricingStrategy pricingStrategy) {
        return new ParkingLot(floors, assignmentStrategy, pricingStrategy);
    }
}
