package ai.shreds.application;

public class ApplicationProductServiceException extends RuntimeException {

    public ApplicationProductServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}