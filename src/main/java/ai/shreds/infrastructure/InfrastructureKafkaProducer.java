package ai.shreds.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.errors.IllegalStateException;
import ai.shreds.infrastructure.InfrastructureException;

@Component
public class InfrastructureKafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureKafkaProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public InfrastructureKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendToKafka(String topic, String message) {
        try {
            kafkaTemplate.send(topic, message);
            logger.info("Message sent to topic {}: {}", topic, message);
        } catch (SerializationException e) {
            logger.error("Serialization error while sending message to topic {}: {}", topic, message, e);
            throw new InfrastructureException("Serialization error while sending message to Kafka", e);
        } catch (IllegalStateException e) {
            logger.error("Illegal state while sending message to topic {}: {}", topic, message, e);
            throw new InfrastructureException("Illegal state while sending message to Kafka", e);
        } catch (Exception e) {
            logger.error("Failed to send message to topic {}: {}", topic, message, e);
            throw new InfrastructureException("Failed to send message to Kafka", e);
        }
    }
}