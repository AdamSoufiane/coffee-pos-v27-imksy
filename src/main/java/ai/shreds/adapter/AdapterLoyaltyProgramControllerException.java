package ai.shreds.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.Arrays;

/**
 * This class handles exceptions for the AdapterLoyaltyProgramController.
 */
@ControllerAdvice
public class AdapterLoyaltyProgramControllerException {

    private static final Logger logger = LoggerFactory.getLogger(AdapterLoyaltyProgramControllerException.class);

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Throwable e) {
        logger.error("Exception occurred: {}. StackTrace: {}", e.getClass().getName(), Arrays.toString(e.getStackTrace()), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage() + ". Exception type: " + e.getClass().getName() + ". StackTrace: " + Arrays.toString(e.getStackTrace()));
    }
}