package ai.shreds.adapter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

@ControllerAdvice
public class AdapterCategoryControllerException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(AdapterCategoryControllerException.class);

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        logger.error("Category not found: ", ex);
        return new ResponseEntity<>("{\"error\": \"Category not found.\"}", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleDataAccessException(DataAccessException ex) {
        logger.error("Database error: ", ex);
        return new ResponseEntity<>("{\"error\": \"Database error occurred.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleInternalServerError(Exception ex) {
        logger.error("Internal server error: ", ex);
        return new ResponseEntity<>("{\"error\": \"Internal server error.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static class NotFoundException extends RuntimeException {
        public NotFoundException(String message) {
            super(message);
        }
    }
}