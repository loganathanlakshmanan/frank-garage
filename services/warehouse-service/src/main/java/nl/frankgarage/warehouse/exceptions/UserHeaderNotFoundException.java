package nl.frankgarage.warehouse.exceptions;

public class UserHeaderNotFoundException extends RuntimeException {
    public UserHeaderNotFoundException(String message) {
        super(message);
    }
}
