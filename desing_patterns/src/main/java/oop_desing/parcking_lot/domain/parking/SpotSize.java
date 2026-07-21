package oop_desing.parcking_lot.domain.parking;

import oop_desing.parcking_lot.domain.vehicle.VehicleType;

public enum SpotSize {
    SMALL, MEDIUM, LARGE;

    public boolean canFit(VehicleType vehicleType) {
        return switch (vehicleType) {
            case MOTORCYCLE -> true;
            case CAR -> this != SMALL;
            case SUV -> this == MEDIUM || this == LARGE;
            case TRUCK -> this == LARGE;
        };
    }
}
