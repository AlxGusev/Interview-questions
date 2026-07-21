package oop_desing.parcking_lot.exeption;

import oop_desing.parcking_lot.domain.vehicle.VehicleType;

public class ParkingFullException extends RuntimeException {
    public ParkingFullException(VehicleType type) {
        super("No available spot for " + type);
    }
}
