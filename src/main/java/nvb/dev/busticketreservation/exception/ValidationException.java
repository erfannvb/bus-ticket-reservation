package nvb.dev.busticketreservation.exception;

public class ValidationException extends IllegalArgumentException {

    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }
}
