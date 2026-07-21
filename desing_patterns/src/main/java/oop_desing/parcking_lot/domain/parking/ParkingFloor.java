package oop_desing.parcking_lot.domain.parking;

import oop_desing.parcking_lot.domain.vehicle.VehicleType;

import java.util.List;

public class ParkingFloor {
    private final int floorNumber;
    private final List<ParkingSpot> spots;

    public ParkingFloor(int floorNumber, List<ParkingSpot> spots) {
        this.floorNumber = floorNumber;
        this.spots = List.copyOf(spots); // защита от внешней мутации
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }

    public List<ParkingSpot> getAvailableSpots(VehicleType type) {
        return spots.stream()
                .filter(ParkingSpot::isAvailable)
                .filter(spot -> spot.canFit(type))
                .toList();
    }
}
