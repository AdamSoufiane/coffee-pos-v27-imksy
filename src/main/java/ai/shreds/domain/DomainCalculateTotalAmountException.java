package ai.shreds.domain;

public class DomainCalculateTotalAmountException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DomainCalculateTotalAmountException() {
        super();
    }

    public DomainCalculateTotalAmountException(String message) {
        super(message);
    }

    public DomainCalculateTotalAmountException(String message, Throwable cause) {
        super(message, cause);
    }

    public DomainCalculateTotalAmountException(Throwable cause) {
        super(cause);
    }
}