package ai.shreds.adapter; 
  
 import ai.shreds.shared.AdapterProductRequestDTO; 
 import ai.shreds.shared.AdapterProductResponseDTO; 
 import ai.shreds.domain.DomainProductEntity; 
 import org.springframework.stereotype.Component; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import java.math.BigDecimal; 
  
 @Component 
 public class AdapterProductMapper { 
  
     private static final Logger logger = LoggerFactory.getLogger(AdapterProductMapper.class); 
  
     public DomainProductEntity toDomain(AdapterProductRequestDTO request) { 
         if (request == null) { 
             return null; 
         } 
         logger.info("Mapping AdapterProductRequestDTO to DomainProductEntity"); 
         validateRequest(request); 
         return DomainProductEntity.builder() 
                 .id(request.getId()) 
                 .name(request.getName()) 
                 .description(request.getDescription()) 
                 .price(request.getPrice()) 
                 .categoryId(request.getCategoryId()) 
                 .stockQuantity(request.getStockQuantity()) 
                 .build(); 
     } 
  
     public AdapterProductResponseDTO toResponse(DomainProductEntity entity) { 
         if (entity == null) { 
             return null; 
         } 
         logger.info("Mapping DomainProductEntity to AdapterProductResponseDTO"); 
         return AdapterProductResponseDTO.builder() 
                 .status("success") 
                 .productId(entity.getId()) 
                 .build(); 
     } 
  
     private void validateRequest(AdapterProductRequestDTO request) { 
         if (request.getPrice() != null && request.getPrice().compareTo(BigDecimal.ZERO) < 0) { 
             throw new IllegalArgumentException("Price must be a positive value."); 
         } 
         if (request.getStockQuantity() != null && request.getStockQuantity() < 0) { 
             throw new IllegalArgumentException("Stock quantity must be a non-negative integer."); 
         } 
     } 
 } 
  
 // Implementation Note: Use Lombok annotations for getters, setters, and builders in the DomainProductEntity class.