package ai.shreds.infrastructure;

import ai.shreds.application.ApplicationNotificationOutputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class InfrastructureKafkaProducer implements ApplicationNotificationOutputPort {

    private static final Logger log = LoggerFactory.getLogger(InfrastructureKafkaProducer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public InfrastructureKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendNotification(String topic, String payload) {
        try {
            kafkaTemplate.send(topic, payload);
            log.info("Sent payload to topic {}: {}", topic, payload);
        } catch (Exception e) {
            log.error("Failed to send payload to topic {}: {}", topic, payload, e);
        }
    }
}