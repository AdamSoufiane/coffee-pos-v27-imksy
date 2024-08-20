package ai.shreds.adapter; 
  
 import ai.shreds.domain.DomainSupplierTransaction; 
 import ai.shreds.domain.DomainProductTransaction; 
 import org.springframework.stereotype.Component; 
 import lombok.extern.slf4j.Slf4j; 
 import lombok.RequiredArgsConstructor; 
 import lombok.Builder; 
 import java.util.List; 
 import java.util.UUID; 
 import java.util.stream.Collectors; 
  
 @Component 
 @Slf4j 
 @RequiredArgsConstructor 
 public class AdapterSupplierTransactionMapper { 
  
     public DomainSupplierTransaction toDomain(AdapterRequestParams request) { 
         if (request == null || request.getSupplierId() == null || request.getTransactionDate() == null || request.getProducts() == null) { 
             throw new IllegalArgumentException("Invalid request parameters"); 
         } 
  
         List<DomainProductTransaction> productTransactions = request.getProducts().stream() 
                 .map(this::toDomain) 
                 .collect(Collectors.toList()); 
  
         log.info("Mapping AdapterRequestParams to DomainSupplierTransaction"); 
  
         return DomainSupplierTransaction.builder() 
                 .id(UUID.randomUUID()) // Assuming ID is generated here 
                 .supplierId(request.getSupplierId()) 
                 .transactionDate(request.getTransactionDate()) 
                 .products(productTransactions) 
                 .build(); 
     } 
  
     private DomainProductTransaction toDomain(AdapterProductTransactionRequest request) { 
         if (request == null || request.getProductId() == null || request.getQuantity() == null || request.getPrice() == null) { 
             throw new IllegalArgumentException("Invalid product transaction request parameters"); 
         } 
  
         log.info("Mapping AdapterProductTransactionRequest to DomainProductTransaction"); 
  
         return DomainProductTransaction.builder() 
                 .id(UUID.randomUUID()) // Assuming ID is generated here 
                 .productId(request.getProductId()) 
                 .quantity(request.getQuantity()) 
                 .price(request.getPrice()) 
                 .build(); 
     } 
 } 
 