package ai.shreds.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;

@RestControllerAdvice
public class AdapterExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(AdapterExceptionHandler.class);

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> handleSQLException(SQLException e) {
        // Log the exception details
        logger.error("SQL Exception occurred: ", e);

        // Return a meaningful response to the client
        String errorMessage = "A database error occurred. Please try again later.";
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        // Log the exception details
        logger.error("Validation Exception occurred: ", e);

        // Return a meaningful response to the client
        String errorMessage = "Validation failed for the request. Please check your input.";
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // Log the exception details
        logger.error("Exception occurred: ", e);

        // Ensure sensitive information is not logged
        String errorMessage = "An unexpected error occurred. Please try again later.";
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}