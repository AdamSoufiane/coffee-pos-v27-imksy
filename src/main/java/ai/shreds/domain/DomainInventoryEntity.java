package ai.shreds.domain; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import javax.persistence.*; 
 import javax.validation.constraints.Min; 
 import java.util.UUID; 
  
 @Data 
 @Entity 
 @Table(name = "inventory") 
 @AllArgsConstructor 
 @NoArgsConstructor 
 public class DomainInventoryEntity { 
  
     @Id 
     @Column(name = "product_id", nullable = false) 
     private UUID productId; 
  
     @Min(value = 0, message = "Available quantity must be a non-negative integer.") 
     @Column(name = "available_quantity", nullable = false) 
     private int availableQuantity; 
  
     @Min(value = 0, message = "Reserved quantity must be a non-negative integer.") 
     @Column(name = "reserved_quantity", nullable = false) 
     private int reservedQuantity; 
  
     @Override 
     public boolean equals(Object o) { 
         if (this == o) return true; 
         if (o == null || getClass() != o.getClass()) return false; 
         DomainInventoryEntity that = (DomainInventoryEntity) o; 
         return productId.equals(that.productId); 
     } 
  
     @Override 
     public int hashCode() { 
         return productId.hashCode(); 
     } 
 } 
 