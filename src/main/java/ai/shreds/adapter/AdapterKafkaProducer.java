package ai.shreds.adapter;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AdapterKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public AdapterKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void produceMessage(String topic, String message) {
        try {
            kafkaTemplate.send(topic, message);
            log.info("Message sent to topic {}: {}", topic, message);
        } catch (Exception e) {
            log.error("Failed to send message to topic {}: {}", topic, message, e);
        }
    }
}