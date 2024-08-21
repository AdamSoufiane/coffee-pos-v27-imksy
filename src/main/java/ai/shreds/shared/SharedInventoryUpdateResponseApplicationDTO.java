package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 /** 
  * Data Transfer Object for Inventory Update Response. 
  */ 
 @Data 
 @AllArgsConstructor 
 @NoArgsConstructor 
 public class SharedInventoryUpdateResponseApplicationDTO { 
     /** 
      * Unique identifier for the product. 
      */ 
     private SharedUUID productId; 
     /** 
      * Updated quantity of the product in inventory. 
      */ 
     private SharedIntegerValueObject updatedQuantity; 
     /** 
      * Timestamp of the last update. 
      */ 
     private SharedTimestamp lastUpdated; 
 }