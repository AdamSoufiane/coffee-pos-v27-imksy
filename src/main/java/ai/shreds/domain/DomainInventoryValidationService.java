package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedInventoryUpdateEventApplicationDTO; 
 import ai.shreds.application.ApplicationInventoryValidationService; 
 import ai.shreds.domain.DomainInventoryUpdateException; 
 import ai.shreds.infrastructure.InfrastructureProductRepositoryPort; 
 import lombok.Getter; 
 import lombok.Setter; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @Getter 
 @Setter 
 public class DomainInventoryValidationService { 
  
     private static final Logger logger = LoggerFactory.getLogger(DomainInventoryValidationService.class); 
  
     @Autowired 
     private InfrastructureProductRepositoryPort productRepository; 
  
     public boolean isValidChange(SharedInventoryUpdateEventApplicationDTO event) { 
         logger.info("Validating inventory change for event: {}", event); 
  
         // Validate that the quantity change is within acceptable limits 
         if (event.getQuantityChange().getValue() == 0) { 
             throw new DomainInventoryUpdateException("Quantity change cannot be zero"); 
         } 
  
         // Get the current product quantity from the repository 
         int currentQuantity = getCurrentProductQuantity(event.getProductId().getUuid()); 
  
         // Check if the event type is 'sale' and the resulting quantity would be negative 
         if ("sale".equals(event.getEventType().getValue()) && (currentQuantity - event.getQuantityChange().getValue() < 0)) { 
             throw new DomainInventoryUpdateException("Insufficient inventory for sale"); 
         } 
  
         // Additional business logic can be added here 
  
         return true; 
     } 
  
     private int getCurrentProductQuantity(String productId) { 
         try { 
             return productRepository.findById(productId).getCurrentQuantity().getValue(); 
         } catch (Exception e) { 
             logger.error("Error fetching product quantity for productId: {}", productId, e); 
             throw new DomainInventoryUpdateException("Error fetching product quantity", e); 
         } 
     } 
 } 
 