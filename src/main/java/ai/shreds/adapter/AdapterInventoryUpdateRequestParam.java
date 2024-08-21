package ai.shreds.adapter; 
  
 import ai.shreds.shared.SharedUUID; 
 import ai.shreds.shared.SharedIntegerValueObject; 
 import ai.shreds.shared.SharedStringValueObject; 
 import ai.shreds.shared.SharedTimestamp; 
 import lombok.AllArgsConstructor; 
 import lombok.Getter; 
 import lombok.NoArgsConstructor; 
 import lombok.Setter; 
 import javax.validation.constraints.NotNull; 
  
 @Getter 
 @Setter 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class AdapterInventoryUpdateRequestParam { 
     @NotNull 
     private SharedUUID id; 
     @NotNull 
     private SharedUUID productId; 
     @NotNull 
     private int quantityChange; 
     @NotNull 
     private String eventType; 
 } 
  
 // Implementation Note: Include Lombok annotations for getters and setters.