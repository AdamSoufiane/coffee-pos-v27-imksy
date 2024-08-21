package ai.shreds.infrastructure;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class InfrastructureProducerFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(InfrastructureProducerFactory.class);
    private final String bootstrapServers;
    private final int retries;
    private final int batchSize;
    private final int lingerMs;
    private final int bufferMemory;

    public InfrastructureProducerFactory(String bootstrapServers, int retries, int batchSize, int lingerMs, int bufferMemory) {
        this.bootstrapServers = bootstrapServers;
        this.retries = retries;
        this.batchSize = batchSize;
        this.lingerMs = lingerMs;
        this.bufferMemory = bufferMemory;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configProps.put(ProducerConfig.RETRIES_CONFIG, retries);
        configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        configProps.put(ProducerConfig.LINGER_MS_CONFIG, lingerMs);
        configProps.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);

        LOGGER.info("Kafka Producer Configurations: {}", configProps);

        return new DefaultKafkaProducerFactory<>(configProps);
    }
}