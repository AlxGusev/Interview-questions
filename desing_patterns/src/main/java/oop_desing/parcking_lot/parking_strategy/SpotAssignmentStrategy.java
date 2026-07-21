package oop_desing.parcking_lot.parking_strategy;

import oop_desing.parcking_lot.domain.parking.ParkingFloor;
import oop_desing.parcking_lot.domain.parking.ParkingSpot;
import oop_desing.parcking_lot.domain.vehicle.VehicleType;

import java.util.List;
import java.util.Optional;

public interface SpotAssignmentStrategy {
    Optional<ParkingSpot> findSpot(List<ParkingFloor> floors, VehicleType vehicleType);
}
