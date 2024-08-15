package ai.shreds.infrastructure;

import ai.shreds.domain.DomainNotificationServicePort;
import ai.shreds.domain.DomainSupplierEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class InfrastructureNotificationServiceImpl implements DomainNotificationServicePort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "SupplierUpdates";
    private static final Logger logger = LoggerFactory.getLogger(InfrastructureNotificationServiceImpl.class);

    public InfrastructureNotificationServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void notifySupplierUpdate(DomainSupplierEntity supplier) {
        String notification = "Supplier updated: " + supplier.getName() + ", ID: " + supplier.getId();
        logger.info("Sending notification for supplier update: {}", notification);
        sendNotification(notification);
    }

    @Override
    public void sendNotification(String notification) {
        try {
            kafkaTemplate.send(TOPIC, notification).addCallback(
                success -> logger.info("Notification sent successfully: {}", notification),
                failure -> {
                    logger.error("Failed to send notification: {}", notification, failure);
                    // Implement retry logic here if needed
                }
            );
        } catch (Exception e) {
            logger.error("Exception occurred while sending notification: {}", notification, e);
            // Handle exception, possibly retry sending the message
        }
    }
}