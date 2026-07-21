package oop_desing.parcking_lot.domain.vehicle;

import java.util.Objects;

public abstract class Vehicle {
    private final VehicleType type;
    private final String licensePlate;

    protected Vehicle(VehicleType type, String licensePlate) {
        this.type = Objects.requireNonNull(type);
        this.licensePlate = Objects.requireNonNull(licensePlate);
    }

    public VehicleType getType() { return type; }
    public String getLicensePlate() { return licensePlate; }
}
