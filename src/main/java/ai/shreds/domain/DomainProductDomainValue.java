package ai.shreds.domain.valueobjects; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import java.util.UUID; 
 import java.sql.Timestamp; 
  
 /** 
  * Represents a product in the domain layer with its attributes. 
  */ 
 @Data 
 @AllArgsConstructor 
 public class DomainProductDomainValue { 
     /** 
      * Unique identifier for the product. 
      */ 
     private final UUID id; 
  
     /** 
      * Name of the product. 
      */ 
     private final String name; 
  
     /** 
      * Current quantity of the product in inventory. 
      */ 
     private final int currentQuantity; 
  
     /** 
      * The last time the inventory was updated for this product. 
      */ 
     private final Timestamp lastUpdated; 
  
     /** 
      * Constructs a DomainProductDomainValue object. 
      * 
      * @param id the unique identifier for the product 
      * @param name the name of the product 
      * @param currentQuantity the current quantity of the product in inventory 
      * @param lastUpdated the last time the inventory was updated for this product 
      * @throws IllegalArgumentException if the current quantity is negative 
      */ 
     public DomainProductDomainValue(UUID id, String name, int currentQuantity, Timestamp lastUpdated) { 
         if (currentQuantity < 0) { 
             throw new IllegalArgumentException("Current quantity cannot be negative"); 
         } 
         this.id = id; 
         this.name = name; 
         this.currentQuantity = currentQuantity; 
         this.lastUpdated = lastUpdated; 
     } 
 } 
  
 // Implementation Note: Use Lombok annotations for immutability