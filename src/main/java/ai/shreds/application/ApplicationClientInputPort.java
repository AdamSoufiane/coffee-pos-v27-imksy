package ai.shreds.application; 
  
 import ai.shreds.adapter.AdapterContactInfo; 
 import ai.shreds.adapter.AdapterAddress; 
 import java.util.UUID; 
 import java.time.LocalDateTime; 
 import lombok.Data; 
  
 /** 
  * Interface for client registration in the application layer. 
  */ 
 public interface ApplicationClientInputPort { 
     /** 
      * Registers a new client. 
      *  
      * @param dto the client data transfer object 
      * @return the registered client data transfer object 
      */ 
     ApplicationClientDTO registerClient(ApplicationClientDTO dto); 
 } 
  
 @Data 
 class ApplicationClientDTO { 
     private UUID clientId; 
     private String firstName; 
     private String lastName; 
     private AdapterContactInfo contact_info; 
     private AdapterAddress address; 
     private LocalDateTime registrationDate; 
 } 
 