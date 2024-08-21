package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedUUID; 
 import ai.shreds.shared.SharedStringValueObject; 
 import ai.shreds.shared.SharedIntegerValueObject; 
 import ai.shreds.shared.SharedTimestamp; 
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import javax.validation.constraints.NotNull; 
  
 /** 
  * Represents a product in the inventory. 
  */ 
 @Data 
 @AllArgsConstructor 
 @NoArgsConstructor 
 public class DomainProductEntity { 
     /** 
      * Unique identifier for the product. 
      */ 
     @NotNull 
     private SharedUUID id; 
  
     /** 
      * Name of the product. 
      */ 
     @NotNull 
     private SharedStringValueObject name; 
  
     /** 
      * Current quantity of the product in inventory. 
      */ 
     @NotNull 
     private SharedIntegerValueObject currentQuantity; 
  
     /** 
      * The last time the inventory was updated for this product. 
      */ 
     @NotNull 
     private SharedTimestamp lastUpdated; 
 }