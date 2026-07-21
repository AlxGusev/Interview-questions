package oop_desing.parcking_lot;

import oop_desing.parcking_lot.domain.parking.ParkingLot;
import oop_desing.parcking_lot.domain.parking.ParkingLotBuilder;
import oop_desing.parcking_lot.domain.parking.SpotSize;
import oop_desing.parcking_lot.domain.ticket.Ticket;
import oop_desing.parcking_lot.domain.vehicle.Car;
import oop_desing.parcking_lot.domain.vehicle.Truck;
import oop_desing.parcking_lot.domain.vehicle.Vehicle;
import oop_desing.parcking_lot.domain.vehicle.VehicleType;
import oop_desing.parcking_lot.parking_strategy.BestFitSpot;
import oop_desing.parcking_lot.pricing_strategy.HourlyPricing;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Map;


public class ParkingLotMain {
    public static void main(String[] args) {
        Map<SpotSize, Integer> layout = Map.of(
                SpotSize.SMALL, 10,
                SpotSize.MEDIUM, 30,
                SpotSize.LARGE, 10
        );

        ParkingLot lot = new ParkingLotBuilder()
                .addFloor(1, layout)
                .addFloor(2, layout)
                .build(
                        new BestFitSpot(),
                        new HourlyPricing(Map.of(
                                VehicleType.CAR, BigDecimal.valueOf(5),
                                VehicleType.TRUCK, BigDecimal.valueOf(10)
                        ))
                );

        Vehicle car = new Car("ABC-123");
        Vehicle truck = new Truck("XYZ-456");
        Ticket carTicket = lot.parkVehicle(car);
        Ticket truckTicket = lot.parkVehicle(truck);
        lot.unparkVehicle(carTicket.getId());
        lot.unparkVehicle(truckTicket.getId());
        System.out.println("Fee: " + carTicket.getFee() + " Duration: " + formatDuration(carTicket.getParkedDuration()));
        System.out.println("Fee: " + truckTicket.getFee() + " Duration: " + formatDuration(truckTicket.getParkedDuration()));
    }

    private static String formatDuration(Duration d) {
        return String.format("%dd %02dh %02dm",
                d.toDaysPart(),
                d.toHoursPart(),
                d.toMinutesPart());
    }
}
