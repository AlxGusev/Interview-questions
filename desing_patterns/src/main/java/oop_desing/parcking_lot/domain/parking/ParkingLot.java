package oop_desing.parcking_lot.domain.parking;

import oop_desing.parcking_lot.domain.ticket.Ticket;
import oop_desing.parcking_lot.domain.vehicle.Vehicle;
import oop_desing.parcking_lot.exeption.ParkingFullException;
import oop_desing.parcking_lot.parking_strategy.SpotAssignmentStrategy;
import oop_desing.parcking_lot.pricing_strategy.PricingStrategy;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class ParkingLot {
    private final List<ParkingFloor> floors;
    private final SpotAssignmentStrategy assignmentStrategy;
    private final PricingStrategy pricingStrategy;
    private final Map<String, Ticket> activeTickets = new ConcurrentHashMap<>();

    public ParkingLot(List<ParkingFloor> floors,
                      SpotAssignmentStrategy assignmentStrategy,
                      PricingStrategy pricingStrategy) {
        this.floors = List.copyOf(floors);
        this.assignmentStrategy = assignmentStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = assignmentStrategy
                .findSpot(floors, vehicle.getType())
                .orElseThrow(() -> new ParkingFullException(vehicle.getType()));

        spot.park(vehicle);
        Ticket ticket = new Ticket(vehicle, spot, Instant.now());
        activeTickets.put(ticket.getId(), ticket);
        return ticket;
    }

    public void unparkVehicle(String ticketId) {
        Ticket ticket = activeTickets.get(ticketId);
        if (ticket == null) {
            throw new NoSuchElementException("Unknown ticket: " + ticketId);
        }

        Instant now = Instant.now().plus(Duration.ofMinutes(ThreadLocalRandom.current().nextLong(60, 400)));
        Duration parkedDuration = Duration.between(ticket.getEntryTime(), now);
        BigDecimal fee = pricingStrategy.calculateFee(ticket.getVehicle().getType(), parkedDuration);

        ticket.close(now, fee);
        ticket.getSpot().unpark();
        activeTickets.remove(ticketId);
    }
}
