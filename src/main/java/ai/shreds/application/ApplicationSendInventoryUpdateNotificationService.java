package ai.shreds.application;

import ai.shreds.adapter.AdapterResponseDTO;
import ai.shreds.application.exception.ApplicationSendInventoryUpdateNotificationException;
import ai.shreds.application.port.ApplicationNotificationOutputPort;
import ai.shreds.application.port.ApplicationSendInventoryUpdateNotificationOutputPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.KafkaException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationSendInventoryUpdateNotificationService implements ApplicationSendInventoryUpdateNotificationOutputPort {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationSendInventoryUpdateNotificationService.class);
    private final ApplicationNotificationOutputPort applicationNotificationOutputPort;
    private final ObjectMapper objectMapper;

    @Override
    public void sendInventoryUpdateNotification(AdapterResponseDTO transaction) {
        try {
            String payload = objectMapper.writeValueAsString(transaction);
            applicationNotificationOutputPort.sendNotification("inventory-update", payload);
            logger.info("Successfully sent inventory update notification for transaction ID: {}", transaction.getId());
        } catch (JsonProcessingException e) {
            logger.error("Failed to serialize transaction data for transaction ID: {}", transaction.getId(), e);
            throw new ApplicationSendInventoryUpdateNotificationException("Failed to serialize transaction data", e);
        } catch (KafkaException e) {
            logger.error("Failed to send inventory update notification for transaction ID: {}", transaction.getId(), e);
            throw new ApplicationSendInventoryUpdateNotificationException("Failed to send inventory update notification", e);
        } catch (Exception e) {
            logger.error("Unexpected error while sending inventory update notification for transaction ID: {}", transaction.getId(), e);
            throw new ApplicationSendInventoryUpdateNotificationException("Failed to send inventory update notification", e);
        }
    }
}