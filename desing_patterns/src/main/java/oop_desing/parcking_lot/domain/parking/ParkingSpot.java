package oop_desing.parcking_lot.domain.parking;

import oop_desing.parcking_lot.domain.vehicle.Vehicle;
import oop_desing.parcking_lot.domain.vehicle.VehicleType;
import oop_desing.parcking_lot.exeption.IncompatibleVehicleException;
import oop_desing.parcking_lot.exeption.SpotAlreadyOccupiedException;

public class ParkingSpot {
    private final int number;
    private final SpotSize size;
    private Vehicle vehicle;

    public ParkingSpot(int number, SpotSize size) {
        this.number = number;
        this.size = size;
    }

    public synchronized void park(Vehicle vehicle) {
        if (this.vehicle != null) {
            throw new SpotAlreadyOccupiedException(number);
        }
        if (!size.canFit(vehicle.getType())) {
            throw new IncompatibleVehicleException(vehicle.getType(), size);
        }
        this.vehicle = vehicle;
    }

    public synchronized void unpark() {
        this.vehicle = null;
    }

    public synchronized boolean isAvailable() {
        return vehicle == null;
    }

    public boolean canFit(VehicleType type) {
        return size.canFit(type);
    }

    public int getNumber() { return number; }
    public SpotSize getSize() { return size; }
}
