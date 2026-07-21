package oop_desing.parcking_lot.pricing_strategy;

import oop_desing.parcking_lot.domain.vehicle.VehicleType;

import java.math.BigDecimal;
import java.time.Duration;

@FunctionalInterface
public interface PricingStrategy {
    BigDecimal calculateFee(VehicleType type, Duration parkedDuration);
}
