package ai.shreds.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ApplicationLoyaltyProgramException {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationLoyaltyProgramException.class);

    public void handleException(Throwable e) {
        // Log the exception details with stack trace
        logger.error("An error occurred in the Loyalty Program Management application layer: {}", e.getMessage(), e);
        // Additional actions to handle the exception can be added here
    }
}