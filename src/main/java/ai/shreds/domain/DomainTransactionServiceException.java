package ai.shreds.domain;

/**
 * Custom exception class for handling errors related to transaction services within the domain layer.
 * This exception is thrown by methods in the DomainTransactionService class when they encounter
 * errors related to saving supplier transactions or calculating total amounts.
 */
public class DomainTransactionServiceException extends Exception {

    /**
     * Constructs a new exception with no detail message.
     */
    public DomainTransactionServiceException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     * @param message the detail message
     */
    public DomainTransactionServiceException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * @param message the detail message
     * @param cause the cause
     */
    public DomainTransactionServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause.
     * @param cause the cause
     */
    public DomainTransactionServiceException(Throwable cause) {
        super(cause);
    }
}