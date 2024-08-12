package ai.shreds.infrastructure;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exception handler for data access exceptions in the Category repository.
 */
@RestControllerAdvice
public class InfrastructureCategoryRepositoryException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureCategoryRepositoryException.class);

    /**
     * Default constructor.
     */
    public InfrastructureCategoryRepositoryException() {
        super();
    }

    /**
     * Constructor with a message.
     * @param message the detail message
     */
    public InfrastructureCategoryRepositoryException(String message) {
        super(message);
    }

    /**
     * Constructor with a message and cause.
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public InfrastructureCategoryRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Handles DataAccessException and returns an appropriate error response.
     * @param ex the DataAccessException
     * @return the error response
     */
    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleDataAccessException(DataAccessException ex) {
        logger.error("A database error occurred: ", ex);
        return new ErrorResponse("INTERNAL_SERVER_ERROR", "A database error occurred: " + ex.getMessage());
    }

    /**
     * Error response class to structure error messages.
     */
    public static class ErrorResponse {
        private String errorCode;
        private String errorMessage;

        /**
         * Constructor with error code and message.
         * @param errorCode the error code
         * @param errorMessage the error message
         */
        public ErrorResponse(String errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }
}