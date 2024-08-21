package ai.shreds.domain;

public class DomainValidationException extends Exception {
    private static final long serialVersionUID = 1L;

    public DomainValidationException(String message) {
        super(message);
    }
}