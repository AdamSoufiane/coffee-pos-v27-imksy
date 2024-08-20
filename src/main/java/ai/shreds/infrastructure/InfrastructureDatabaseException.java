package ai.shreds.infrastructure;

public class InfrastructureDatabaseException extends Exception {
    private static final long serialVersionUID = 1L;

    public InfrastructureDatabaseException() {
        super();
    }

    public InfrastructureDatabaseException(String message) {
        super(message);
    }

    public InfrastructureDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public InfrastructureDatabaseException(Throwable cause) {
        super(cause);
    }
}