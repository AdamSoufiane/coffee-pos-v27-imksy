package ai.shreds.domain;

/**
 * Exception thrown when a parent category is not found.
 */
public class ParentCategoryNotFoundException extends RuntimeException {

    /**
     * Constructs a new ParentCategoryNotFoundException with the specified detail message.
     *
     * @param message the detail message.
     */
    public ParentCategoryNotFoundException(String message) {
        super(message);
    }
}