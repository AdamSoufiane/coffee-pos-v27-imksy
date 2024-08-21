package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Getter; 
 import lombok.ToString; 
  
 /** 
  * Data Transfer Object representing an inventory update event in the application layer. 
  */ 
 @Getter 
 @AllArgsConstructor 
 @ToString 
 public class SharedInventoryUpdateEventApplicationDTO { 
     /** 
      * Unique identifier for the inventory update event. 
      */ 
     private final SharedUUID id; 
  
     /** 
      * Identifier for the product associated with this inventory update. 
      */ 
     private final SharedUUID productId; 
  
     /** 
      * The change in quantity (positive for restocking, negative for sales). 
      */ 
     private final SharedIntegerValueObject quantityChange; 
  
     /** 
      * Type of event triggering the update (e.g., 'sale', 'restock'). 
      */ 
     private final SharedStringValueObject eventType; 
  
     /** 
      * The time at which the event was created. 
      */ 
     private final SharedTimestamp timestamp; 
 } 
 