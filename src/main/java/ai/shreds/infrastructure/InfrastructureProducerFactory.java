package ai.shreds.infrastructure; 
  
 import org.apache.kafka.clients.producer.ProducerConfig; 
 import org.apache.kafka.common.serialization.StringSerializer; 
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import org.springframework.kafka.core.DefaultKafkaProducerFactory; 
 import org.springframework.kafka.core.ProducerFactory; 
 import java.util.HashMap; 
 import java.util.Map; 
  
 @Configuration 
 public class InfrastructureProducerFactory { 
  
     @Bean 
     public ProducerFactory<String, String> producerFactory() { 
         Map<String, Object> configProps = new HashMap<>(); 
         configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); 
         configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); 
         configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); 
         configProps.put(ProducerConfig.RETRIES_CONFIG, 3); 
         configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384); 
         configProps.put(ProducerConfig.LINGER_MS_CONFIG, 1); 
         configProps.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432); 
         // Add logging for Kafka producer configurations 
         System.out.println("Kafka Producer Configurations: " + configProps); 
         try { 
             return new DefaultKafkaProducerFactory<>(configProps); 
         } catch (Exception e) { 
             // Add logging for errors 
             System.err.println("Failed to configure Kafka producer: " + e.getMessage()); 
             throw new RuntimeException("Failed to configure Kafka producer", e); 
         } 
     } 
 } 
 