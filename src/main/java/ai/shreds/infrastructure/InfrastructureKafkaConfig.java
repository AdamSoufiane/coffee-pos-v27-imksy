package ai.shreds.infrastructure;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class InfrastructureKafkaConfig {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureKafkaConfig.class);

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * Creates a ProducerFactory with the necessary configurations for Kafka.
     * @return ProducerFactory<String, Object>
     */
    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        // Validate Kafka properties
        validateKafkaProperties(configProps);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /**
     * Creates a KafkaTemplate using the configured ProducerFactory.
     * @return KafkaTemplate<String, Object>
     */
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        KafkaTemplate<String, Object> kafkaTemplate = new KafkaTemplate<>(producerFactory());
        kafkaTemplate.setProducerListener(new KafkaProducerListener());
        return kafkaTemplate;
    }

    /**
     * Validates the Kafka configuration properties to ensure they are correctly set.
     * @param configProps The configuration properties map
     */
    private void validateKafkaProperties(Map<String, Object> configProps) {
        if (!configProps.containsKey(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG)) {
            throw new IllegalArgumentException("Bootstrap servers not configured");
        } else if (!configProps.containsKey(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG)) {
            throw new IllegalArgumentException("Key serializer not configured");
        } else if (!configProps.containsKey(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG)) {
            throw new IllegalArgumentException("Value serializer not configured");
        }
    }

    /**
     * KafkaProducerListener to handle success and error callbacks for Kafka message production.
     */
    private static class KafkaProducerListener implements org.springframework.kafka.support.ProducerListener<String, Object> {

        @Override
        public void onSuccess(String topic, Integer partition, String key, Object value, org.apache.kafka.clients.producer.RecordMetadata recordMetadata) {
            logger.info("Message sent successfully to topic {} partition {} with key {}", topic, partition, key);
        }

        @Override
        public void onError(String topic, Integer partition, String key, Object value, Exception exception) {
            logger.error("Error sending message to topic {} partition {} with key {}", topic, partition, key, exception);
        }
    }
}