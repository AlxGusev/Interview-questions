package oop_desing.parcking_lot.domain.ticket;

import oop_desing.parcking_lot.domain.parking.ParkingSpot;
import oop_desing.parcking_lot.domain.vehicle.Vehicle;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

public class Ticket {
    private final String id;
    private final Vehicle vehicle;
    private final ParkingSpot spot;
    private final Instant entryTime;
    private Instant exitTime;
    private TicketStatus status;

    private BigDecimal fee;

    public Ticket(Vehicle vehicle, ParkingSpot spot, Instant entryTime) {
        this.id = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTime = entryTime;
        this.status = TicketStatus.ACTIVE;
    }

    public void close(Instant exitTime, BigDecimal fee) {
        if (status != TicketStatus.ACTIVE) {
            throw new IllegalStateException("Ticket " + id + " is not active");
        }
        if (fee == null || fee.signum() < 0) {
            throw new IllegalArgumentException("Fee must be non-negative");
        }
        this.exitTime = exitTime;
        this.fee = fee;
        this.status = TicketStatus.CLOSED;
    }

    public Duration getParkedDuration() {
        Instant end = (exitTime != null) ? exitTime : Instant.now();
        return Duration.between(entryTime, end);
    }

    public String getId() { return id; }
    public Vehicle getVehicle() { return vehicle; }

    public Instant getEntryTime() {
        return entryTime;
    }

    public ParkingSpot getSpot() { return spot; }
    public TicketStatus getStatus() { return status; }

    public BigDecimal getFee() {
        return fee;
    }
}
