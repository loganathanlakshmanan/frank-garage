package nl.frankgarage.warehouse.exceptions;

public class UsedCarNotFoundException extends RuntimeException {

    public UsedCarNotFoundException(String message) {
        super(message);
    }
}
