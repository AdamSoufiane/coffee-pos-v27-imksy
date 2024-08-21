package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import javax.validation.constraints.NotNull; 
  
 /** 
  * Data Transfer Object for Inventory Update Event in the domain layer. 
  */ 
 @Data 
 @AllArgsConstructor 
 @NoArgsConstructor 
 public class SharedInventoryUpdateEventDomainDTO { 
     /** 
      * Unique identifier for the inventory update event. 
      */ 
     @NotNull 
     private SharedUUID id; 
  
     /** 
      * Identifier for the product associated with this inventory update. 
      */ 
     @NotNull 
     private SharedUUID productId; 
  
     /** 
      * The change in quantity (positive for restocking, negative for sales). 
      */ 
     @NotNull 
     private SharedIntegerValueObject quantityChange; 
  
     /** 
      * Type of event triggering the update (e.g., 'sale', 'restock'). 
      */ 
     @NotNull 
     private SharedStringValueObject eventType; 
  
     /** 
      * The time at which the event was created. 
      */ 
     @NotNull 
     private SharedTimestamp timestamp; 
 }