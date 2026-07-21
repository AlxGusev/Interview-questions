package oop_desing.parcking_lot.exeption;

import oop_desing.parcking_lot.domain.parking.SpotSize;
import oop_desing.parcking_lot.domain.vehicle.VehicleType;

public class IncompatibleVehicleException extends RuntimeException {
    public IncompatibleVehicleException(VehicleType type, SpotSize size) {
        super(type + " cannot fit in a " + size + " spot");
    }
}
