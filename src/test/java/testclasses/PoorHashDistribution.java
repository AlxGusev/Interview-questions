package testclasses;

import java.util.Objects;

public class PoorHashDistribution {

    private final static String POOR_HASH = "POOR_HASH";

    private final String value;

    public PoorHashDistribution(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

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
