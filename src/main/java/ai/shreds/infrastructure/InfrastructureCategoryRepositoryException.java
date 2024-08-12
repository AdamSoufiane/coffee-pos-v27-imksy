package ai.shreds.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.http.ResponseEntity;

@Component
public class InfrastructureCategoryRepositoryException {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureCategoryRepositoryException.class);

    public ResponseEntity<String> handleException(Exception e) {
        // Log the exception details
        logger.error("Exception occurred in Category Repository: ", e);
        // Return a meaningful error message with more context
        return ResponseEntity.status(500).body("An error occurred in the Category Repository: " + e.getMessage());
    }
}