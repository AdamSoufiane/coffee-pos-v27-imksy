package ai.shreds.adapter; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Builder; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import javax.validation.constraints.Min; 
 import javax.validation.constraints.NotNull; 
 import javax.validation.constraints.Size; 
 import java.math.BigDecimal; 
 import java.util.UUID; 
  
 @Data 
 @Builder 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class AdapterCreateProductRequest { 
     @NotNull 
     @Size(min = 1, max = 255) 
     private String name; 
  
     @Size(max = 500) 
     private String description; 
  
     @NotNull 
     @Min(value = 0, message = "Price must be positive") 
     private BigDecimal price; 
  
     @NotNull 
     private UUID category_id; 
  
     @NotNull 
     @Min(value = 0, message = "Stock quantity must be non-negative") 
     private Integer stock_quantity; 
 } 
 