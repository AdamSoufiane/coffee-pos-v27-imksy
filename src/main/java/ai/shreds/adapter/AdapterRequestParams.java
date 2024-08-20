package ai.shreds.adapter; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Builder; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import java.sql.Timestamp; 
 import java.util.List; 
 import java.util.UUID; 
  
 @Data 
 @Builder 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class AdapterRequestParams { 
     private UUID supplierId; 
     private Timestamp transactionDate; 
     private List<AdapterProductTransactionRequest> products; 
     // Note: Include Lombok annotations for getters, setters, constructors, and builders 
 } 
 