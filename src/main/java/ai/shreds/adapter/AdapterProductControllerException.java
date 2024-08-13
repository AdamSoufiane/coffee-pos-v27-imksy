package ai.shreds.adapter;

import ai.shreds.shared.AdapterProductResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception handler for AdapterProductController.
 * This class handles various exceptions that might occur in the controller
 * and maps them to appropriate HTTP responses.
 */
@RestControllerAdvice
public class AdapterProductControllerException {

    private static final Logger logger = LoggerFactory.getLogger(AdapterProductControllerException.class);

    /**
     * Handles generic exceptions.
     *
     * @param exception the exception thrown
     * @return a ResponseEntity containing the error message and HTTP status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<AdapterProductResponse> handleException(Exception exception) {
        AdapterProductResponse response = new AdapterProductResponse();
        if (exception instanceof IllegalArgumentException) {
            logger.error("IllegalArgumentException: {}", exception.getMessage());
            response.setMessage("Invalid product data: " + exception.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else if (exception instanceof NullPointerException) {
            logger.error("NullPointerException: {}", exception.getMessage());
            response.setMessage("A required value was null: " + exception.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else if (exception instanceof IllegalStateException) {
            logger.error("IllegalStateException: {}", exception.getMessage());
            response.setMessage("Illegal state: " + exception.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } else {
            logger.error("Exception: {}", exception.getMessage());
            response.setMessage("An unexpected error occurred while processing your request.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}