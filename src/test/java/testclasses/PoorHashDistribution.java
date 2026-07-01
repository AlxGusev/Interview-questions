package testclasses;

import java.util.Objects;

public record PoorHashDistribution(String value) {

    private final static String POOR_HASH = "POOR_HASH";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoorHashDistribution that = (PoorHashDistribution) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(POOR_HASH);
    }
}
