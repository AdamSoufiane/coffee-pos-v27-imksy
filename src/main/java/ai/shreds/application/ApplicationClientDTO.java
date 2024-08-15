package ai.shreds.application; 
  
 import ai.shreds.shared.AdapterAddress; 
 import ai.shreds.shared.AdapterContactInfo; 
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import java.time.LocalDateTime; 
 import java.util.UUID; 
  
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class ApplicationClientDTO { 
     private UUID clientId; 
     private String firstName; 
     private String lastName; 
     private AdapterContactInfo contact_info; 
     private AdapterAddress address; 
     private LocalDateTime registrationDate; 
 } 
  
 // Implementation Notes: 
 // - Add Lombok annotations for getters and setters. 
 // - Add Jackson annotations for JSON serialization/deserialization.