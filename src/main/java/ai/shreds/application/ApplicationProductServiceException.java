package ai.shreds.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ai.shreds.adapter.AdapterProductResponse;

@RestControllerAdvice
public class ApplicationProductServiceException {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationProductServiceException.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AdapterProductResponse> handleException(Exception exception) {
        logger.error("Exception occurred: ", exception);

        AdapterProductResponse response = AdapterProductResponse.builder()
            .message("An unexpected error occurred while processing your request.")
            .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<AdapterProductResponse> handleProductNotFoundException(ProductNotFoundException exception) {
        logger.error("Product not found: ", exception);

        AdapterProductResponse response = AdapterProductResponse.builder()
            .message("Product not found.")
            .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<AdapterProductResponse> handleValidationException(ValidationException exception) {
        logger.error("Validation error: ", exception);

        AdapterProductResponse response = AdapterProductResponse.builder()
            .message("Invalid product data: " + exception.getMessage())
            .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}

class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}