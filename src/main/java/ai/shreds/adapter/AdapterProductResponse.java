package ai.shreds.adapter; 
  
 import lombok.Builder; 
 import lombok.Data; 
  
 import java.math.BigDecimal; 
 import java.sql.Timestamp; 
 import java.util.UUID; 
  
 /** 
  * AdapterProductResponse is a data transfer object (DTO) that represents the response structure for product-related API operations. 
  * It includes fields such as id, name, description, price, category_id, stock_quantity, created_at, and updated_at. 
  */ 
 @Data 
 @Builder 
 public class AdapterProductResponse { 
     /** 
      * Unique identifier for the product. 
      */ 
     private UUID id; 
  
     /** 
      * Name of the product. 
      */ 
     private String name; 
  
     /** 
      * Description of the product. 
      */ 
     private String description; 
  
     /** 
      * Price of the product. 
      */ 
     private BigDecimal price; 
  
     /** 
      * Identifier for the category the product belongs to. 
      */ 
     private UUID category_id; 
  
     /** 
      * Quantity of the product in stock. 
      */ 
     private Integer stock_quantity; 
  
     /** 
      * Timestamp when the product was created. 
      */ 
     private Timestamp created_at; 
  
     /** 
      * Timestamp when the product was last updated. 
      */ 
     private Timestamp updated_at; 
 }