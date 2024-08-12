package ai.shreds.shared;

public class CustomValidationException extends RuntimeException {

    public CustomValidationException(String message) {
        super(message);
    }
}