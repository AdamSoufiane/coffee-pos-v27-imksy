package ai.shreds.application; 
  
 import ai.shreds.adapter.AdapterResponseDTO; 
  
 /** 
  * Interface for sending inventory update notifications. 
  */ 
 public interface ApplicationSendInventoryUpdateNotificationOutputPort { 
     /** 
      * Sends a notification about a stored supplier transaction to another service via Kafka. 
      * 
      * @param transaction the transaction details to be sent as a notification 
      */ 
     void sendInventoryUpdateNotification(AdapterResponseDTO transaction); 
 } 
 