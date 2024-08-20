package ai.shreds.adapter; 
  
 import ai.shreds.domain.DomainProductTransaction; 
 import java.util.UUID; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 public class AdapterProductTransactionMapper { 
  
     public DomainProductTransaction toDomain(AdapterProductTransactionRequest request) { 
         if (request == null) { 
             return null; 
         } 
         DomainProductTransaction domainProductTransaction = new DomainProductTransaction(); 
         domainProductTransaction.setProductId(request.getProductId()); 
         domainProductTransaction.setQuantity(request.getQuantity()); 
         domainProductTransaction.setPrice(request.getPrice()); 
         return domainProductTransaction; 
     } 
 } 
  
 @Data 
 @NoArgsConstructor 
 class AdapterProductTransactionRequest { 
     private UUID productId; 
     private int quantity; 
     private double price; 
 } 
  
 @Data 
 @NoArgsConstructor 
 class DomainProductTransaction { 
     private UUID productId; 
     private int quantity; 
     private double price; 
 } 
  
 // Note: Lombok annotations (@Data, @NoArgsConstructor) are used to reduce boilerplate code for getters, setters, and constructors.