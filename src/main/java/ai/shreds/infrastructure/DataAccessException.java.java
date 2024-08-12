package ai.shreds.infrastructure;

public class DataAccessException extends RuntimeException {
    private final String errorCode;

    public DataAccessException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}