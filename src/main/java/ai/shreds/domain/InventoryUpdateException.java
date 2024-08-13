package ai.shreds.domain;

public class InventoryUpdateException extends RuntimeException {
    public InventoryUpdateException(String message) {
        super(message);
    }

    public InventoryUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}