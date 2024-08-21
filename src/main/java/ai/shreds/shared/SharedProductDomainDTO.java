package ai.shreds.shared; 
  
 import lombok.Builder; 
 import lombok.Data; 
  
 /** 
  * Data Transfer Object (DTO) representing a product in the inventory domain. 
  */ 
 @Data 
 @Builder 
 public class SharedProductDomainDTO { 
     /** 
      * Unique identifier for the product. 
      */ 
     private SharedUUID id; 
  
     /** 
      * Name of the product. 
      */ 
     private SharedStringValueObject name; 
  
     /** 
      * Current quantity of the product in inventory. 
      */ 
     private SharedIntegerValueObject currentQuantity; 
  
     /** 
      * The last time the inventory was updated for this product. 
      */ 
     private SharedTimestamp lastUpdated; 
 } 
 