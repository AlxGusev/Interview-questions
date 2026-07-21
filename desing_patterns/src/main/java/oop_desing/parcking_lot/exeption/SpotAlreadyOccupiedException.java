package oop_desing.parcking_lot.exeption;

public class SpotAlreadyOccupiedException extends RuntimeException {
    public SpotAlreadyOccupiedException(int spotNumber) {
        super("Spot " + spotNumber + " is already occupied");
    }
}
