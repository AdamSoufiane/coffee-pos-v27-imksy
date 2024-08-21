package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedUUID; 
 import ai.shreds.shared.SharedIntegerValueObject; 
 import ai.shreds.shared.SharedStringValueObject; 
 import ai.shreds.shared.SharedTimestamp; 
 import lombok.Getter; 
 import lombok.AllArgsConstructor; 
  
 /** 
  * DomainInventoryUpdateEventDomainDTO represents a Data Transfer Object (DTO) for Inventory Update Events. 
  * This DTO is used to transfer data between different layers of the application, particularly between the domain and the application layers. 
  */ 
 @Getter 
 @AllArgsConstructor 
 public class DomainInventoryUpdateEventDomainDTO { 
     private final SharedUUID id; 
     private final SharedUUID productId; 
     private final SharedIntegerValueObject quantityChange; 
     private final SharedStringValueObject eventType; 
     private final SharedTimestamp timestamp; 
 } 
 