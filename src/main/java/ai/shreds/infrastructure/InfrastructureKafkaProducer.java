package ai.shreds.infrastructure; 
  
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.kafka.core.KafkaTemplate; 
 import org.springframework.stereotype.Component; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
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
         } catch (Exception e) { 
             logger.error("Failed to send message to topic {}: {}", topic, message, e); 
             throw new InfrastructureException("Failed to send message to Kafka", e); 
         } 
     } 
 } 
  
 // Note: Consider using Lombok annotations for logging. 
 // Note: Handle Kafka specific exceptions distinctly.