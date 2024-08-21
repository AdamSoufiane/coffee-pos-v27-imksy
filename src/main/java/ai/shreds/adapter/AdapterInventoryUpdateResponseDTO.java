package ai.shreds.adapter; 
  
 import ai.shreds.shared.SharedUUID; 
 import ai.shreds.shared.SharedTimestamp; 
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 /** 
  * Data Transfer Object for inventory update response. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class AdapterInventoryUpdateResponseDTO { 
     /** 
      * Unique identifier of the product. 
      */ 
     private SharedUUID productId; 
      
     /** 
      * New quantity of the product after the update. 
      */ 
     private int updatedQuantity; 
      
     /** 
      * Timestamp when the inventory was last updated. 
      */ 
     private SharedTimestamp lastUpdated; 
 }