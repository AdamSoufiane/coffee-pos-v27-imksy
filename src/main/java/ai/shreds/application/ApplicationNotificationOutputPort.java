package ai.shreds.application; 
  
 /** 
  * Interface for sending notifications, specifically Kafka messages, 
  * to notify other services about inventory updates based on stored transactions. 
  */ 
 public interface ApplicationNotificationOutputPort { 
     /** 
      * Sends a notification message to a specified topic. 
      * 
      * @param topic   the topic to which the message should be sent 
      * @param payload the message payload to be sent 
      */ 
     void sendNotification(String topic, String payload); 
 }