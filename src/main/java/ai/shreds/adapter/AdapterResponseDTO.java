package ai.shreds.adapter; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Builder; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import java.math.BigDecimal; 
 import java.sql.Timestamp; 
 import java.util.ArrayList; 
 import java.util.List; 
 import java.util.UUID; 
  
 /** 
  * DTO representing the response structure when storing a supplier transaction. 
  */ 
 @Data 
 @Builder 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public final class AdapterResponseDTO { 
  
     /** 
      * Unique identifier for the transaction. 
      */ 
     private UUID id; 
  
     /** 
      * Unique identifier for the supplier. 
      */ 
     private UUID supplierId; 
  
     /** 
      * Timestamp indicating when the transaction occurred. 
      */ 
     private Timestamp transactionDate; 
  
     /** 
      * The total amount paid for the transaction. 
      */ 
     private BigDecimal totalAmount; 
  
     /** 
      * List of product transactions involved in the supplier transaction. 
      */ 
     @Builder.Default 
     private List<AdapterProductTransactionResponse> products = new ArrayList<>(); 
  
     /** 
      * DTO representing the response structure for each product transaction. 
      */ 
     @Data 
     @Builder 
     @NoArgsConstructor 
     @AllArgsConstructor 
     public static class AdapterProductTransactionResponse { 
  
         /** 
          * Unique identifier for the product. 
          */ 
         private UUID productId; 
  
         /** 
          * The quantity of the product involved in the transaction. 
          */ 
         private int quantity; 
  
         /** 
          * The price of the product at the time of the transaction. 
          */ 
         private BigDecimal price; 
     } 
 }