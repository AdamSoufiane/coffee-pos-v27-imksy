package ai.shreds.infrastructure;

import ai.shreds.domain.DomainProductEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InfrastructureKafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureKafkaProducer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${kafka.topic.product-updates}")
    private String productUpdatesTopic;

    public InfrastructureKafkaProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public void produceUpdateMessage(DomainProductEntity product) {
        try {
            String productJson = objectMapper.writeValueAsString(product);
            kafkaTemplate.send(productUpdatesTopic, product.getId().toString(), productJson);
            logger.info("Successfully produced message to Kafka for product: {}", product.getId());
        } catch (JsonProcessingException e) {
            logger.error("Failed to serialize product entity", e);
            throw new RuntimeException("Failed to serialize product entity", e);
        } catch (KafkaException e) {
            logger.error("Failed to produce message to Kafka", e);
            throw new RuntimeException("Failed to produce message to Kafka", e);
        }
    }
}