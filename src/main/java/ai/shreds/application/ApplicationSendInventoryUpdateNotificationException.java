package ai.shreds.application;

/**
 * Exception thrown when there is an error sending inventory update notifications.
 */
public class ApplicationSendInventoryUpdateNotificationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new exception with {@code null} as its detail message.
     */
    public ApplicationSendInventoryUpdateNotificationException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message
     */
    public ApplicationSendInventoryUpdateNotificationException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public ApplicationSendInventoryUpdateNotificationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause.
     *
     * @param cause the cause
     */
    public ApplicationSendInventoryUpdateNotificationException(Throwable cause) {
        super(cause);
    }
}