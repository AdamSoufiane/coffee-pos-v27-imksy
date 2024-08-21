package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedUUID; 
 import ai.shreds.shared.SharedIntegerValueObject; 
 import ai.shreds.shared.SharedStringValueObject; 
 import ai.shreds.shared.SharedTimestamp; 
 import lombok.Getter; 
 import java.util.Objects; 
  
 @Getter 
 public class DomainInventoryUpdateEventDomainValue { 
     private final SharedUUID id; 
     private final SharedUUID productId; 
     private final SharedIntegerValueObject quantityChange; 
     private final SharedStringValueObject eventType; 
     private final SharedTimestamp timestamp; 
  
     public DomainInventoryUpdateEventDomainValue(SharedUUID id, SharedUUID productId, SharedIntegerValueObject quantityChange, SharedStringValueObject eventType, SharedTimestamp timestamp) { 
         this.id = Objects.requireNonNull(id, "id must not be null"); 
         this.productId = Objects.requireNonNull(productId, "productId must not be null"); 
         this.quantityChange = Objects.requireNonNull(quantityChange, "quantityChange must not be null"); 
         this.eventType = Objects.requireNonNull(eventType, "eventType must not be null"); 
         this.timestamp = Objects.requireNonNull(timestamp, "timestamp must not be null"); 
         validateUUID(id); 
         validateUUID(productId); 
     } 
  
     private void validateUUID(SharedUUID uuid) { 
         if (!uuid.getUuid().matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")) { 
             throw new IllegalArgumentException("Invalid UUID format: " + uuid.getUuid()); 
         } 
     } 
 }