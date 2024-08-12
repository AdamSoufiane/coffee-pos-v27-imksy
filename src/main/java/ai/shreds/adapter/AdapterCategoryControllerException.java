package ai.shreds.adapter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.dao.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;

@ControllerAdvice
public class AdapterCategoryControllerException {

    private static final Logger logger = LoggerFactory.getLogger(AdapterCategoryControllerException.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        logger.error("Exception occurred: ", e);
        if (e instanceof IllegalArgumentException) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } else if (e instanceof NullPointerException) {
            return new ResponseEntity<>("Null pointer exception occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (e instanceof DataAccessException) {
            return new ResponseEntity<>("Database access error", HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (e instanceof EntityNotFoundException) {
            return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
        } else if (e instanceof CustomCategoryException) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("Unexpected exception occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException e) {
        logger.error("Validation Exception occurred: ", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}