package ai.shreds.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Handles exceptions that occur within the application service layer.
 */
@RestControllerAdvice
public class ApplicationCategoryServiceException {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationCategoryServiceException.class);

    /**
     * Handles NotFoundException and returns a 404 status.
     *
     * @param ex the NotFoundException
     * @return the error message
     */
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NotFoundException ex) {
        logger.error("Not Found Exception: ", ex);
        return ex.getMessage();
    }

    /**
     * Handles InternalServerErrorException and returns a 500 status.
     *
     * @param ex the InternalServerErrorException
     * @return the error message
     */
    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleInternalServerError(InternalServerErrorException ex) {
        logger.error("Internal Server Error: ", ex);
        return ex.getMessage();
    }
}

/**
 * Custom exception for not found errors.
 */
class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}

/**
 * Custom exception for internal server errors.
 */
class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(String message) {
        super(message);
    }
}