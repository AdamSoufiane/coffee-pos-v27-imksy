package ai.shreds.infrastructure; 
  
 import ai.shreds.application.ApplicationNotificationOutputPort; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.kafka.core.KafkaTemplate; 
 import org.springframework.stereotype.Component; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @Component 
 public class InfrastructureKafkaProducer implements ApplicationNotificationOutputPort { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureKafkaProducer.class); 
  
     private final KafkaTemplate<String, String> kafkaTemplate; 
  
     @Autowired 
     public InfrastructureKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) { 
         this.kafkaTemplate = kafkaTemplate; 
     } 
  
     @Override 
     public void sendNotification(String topic, String payload) { 
         try { 
             kafkaTemplate.send(topic, payload); 
             logger.info("Sent payload to topic {}: {}", topic, payload); 
         } catch (Exception e) { 
             logger.error("Failed to send payload to topic {}: {}", topic, payload, e); 
         } 
     } 
 } 
 // Note: Use Lombok annotations for logging and dependency injection to reduce boilerplate code.