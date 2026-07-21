package oop_desing.parcking_lot.pricing_strategy;

import oop_desing.parcking_lot.domain.vehicle.VehicleType;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Map;

public class HourlyPricing implements PricingStrategy {
    private final Map<VehicleType, BigDecimal> hourlyRates;

    public HourlyPricing(Map<VehicleType, BigDecimal> hourlyRates) {
        this.hourlyRates = Map.copyOf(hourlyRates);
    }

    @Override
    public BigDecimal calculateFee(VehicleType type, Duration parkedDuration) {
        long hours = Math.max(1, parkedDuration.toHours() + (parkedDuration.toMinutesPart() > 0 ? 1 : 0));
        BigDecimal rate = hourlyRates.getOrDefault(type, BigDecimal.ZERO);
        return rate.multiply(BigDecimal.valueOf(hours));
    }
}
