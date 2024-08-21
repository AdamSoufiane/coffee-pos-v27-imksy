package ai.shreds.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ai.shreds.infrastructure.APIError;

@ControllerAdvice
public class InfrastructureExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureExceptionHandler.class);

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<APIError> handleException(Throwable e) {
        // Convert the Throwable to a custom InfrastructureException
        InfrastructureException infrastructureException = new InfrastructureException("An error occurred in the infrastructure layer", e);
        // Log the exception details with more context information
        logger.error("An error occurred in the infrastructure layer: ", infrastructureException);
        // Return error response entity
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIError(HttpStatus.INTERNAL_SERVER_ERROR.value(), infrastructureException.getMessage()));
    }
}

class InfrastructureException extends RuntimeException {
    public InfrastructureException(String message, Throwable cause) {
        super(message, cause);
    }
}