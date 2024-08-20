package ai.shreds.application;

/**
 * Custom exception for handling errors during the process of storing supplier transactions.
 */
public class ApplicationStoreSupplierTransactionException extends RuntimeException {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message
     */
    public ApplicationStoreSupplierTransactionException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public ApplicationStoreSupplierTransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause.
     *
     * @param cause the cause
     */
    public ApplicationStoreSupplierTransactionException(Throwable cause) {
        super(cause);
    }
}