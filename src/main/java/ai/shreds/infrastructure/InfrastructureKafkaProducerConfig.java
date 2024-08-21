package ai.shreds.infrastructure; 
  
 import org.apache.kafka.clients.producer.ProducerConfig; 
 import org.apache.kafka.common.serialization.StringSerializer; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import org.springframework.kafka.core.DefaultKafkaProducerFactory; 
 import org.springframework.kafka.core.KafkaTemplate; 
 import org.springframework.kafka.core.ProducerFactory; 
 import org.springframework.kafka.support.serializer.ErrorHandlingSerializer; 
 import org.springframework.kafka.support.serializer.JsonSerializer; 
  
 import java.util.HashMap; 
 import java.util.Map; 
  
 @Configuration 
 public class InfrastructureKafkaProducerConfig { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureKafkaProducerConfig.class); 
  
     @Bean 
     public ProducerFactory<String, String> producerFactory() { 
         Map<String, Object> configProps = new HashMap<>(); 
         try { 
             String bootstrapServers = "localhost:9092"; 
             if (bootstrapServers == null || bootstrapServers.isEmpty()) { 
                 throw new IllegalArgumentException("Bootstrap servers cannot be null or empty"); 
             } 
             configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers); 
             configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); 
             configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName()); 
             configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false); 
         } catch (Exception e) { 
             logger.error("Error configuring Kafka producer", e); 
             throw new RuntimeException("Kafka producer configuration failed", e); 
         } 
         return new DefaultKafkaProducerFactory<>(configProps); 
     } 
  
     @Bean 
     public KafkaTemplate<String, String> kafkaTemplate() { 
         return new KafkaTemplate<>(producerFactory()); 
     } 
 } 
 