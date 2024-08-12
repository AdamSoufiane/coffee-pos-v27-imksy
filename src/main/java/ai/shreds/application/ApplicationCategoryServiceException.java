package ai.shreds.application;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.EntityNotFoundException;

@Component
public class ApplicationCategoryServiceException {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationCategoryServiceException.class);

    public String handleException(Exception e) {
        logger.error("Exception occurred: ", e);

        if (e instanceof IllegalArgumentException) {
            return "Invalid argument provided.";
        } else if (e instanceof EntityNotFoundException) {
            return "Category not found.";
        } else if (e instanceof NullPointerException) {
            return "A required value was null.";
        } else if (e instanceof DataIntegrityViolationException) {
            return "Data integrity violation.";
        } else {
            return "An unexpected error occurred.";
        }
    }
}