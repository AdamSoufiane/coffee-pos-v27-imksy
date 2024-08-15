package ai.shreds.domain; 
  
 import ai.shreds.domain.DomainSupplierEntity; 
  
 /** 
  * Interface for sending notifications related to supplier updates. 
  */ 
 public interface DomainNotificationServicePort { 
     /** 
      * Sends a notification about the supplier update. 
      * 
      * @param supplier the supplier entity that was updated 
      */ 
     void notifySupplierUpdate(DomainSupplierEntity supplier); 
  
     /** 
      * Sends a generic notification message. 
      * 
      * @param notification the notification message to be sent 
      */ 
     void sendNotification(String notification); 
 } 
 