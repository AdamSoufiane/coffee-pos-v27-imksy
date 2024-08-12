package ai.shreds.domain;

public class ParentCategoryNotFoundException extends RuntimeException {
    public ParentCategoryNotFoundException(String message) {
        super(message);
    }
}