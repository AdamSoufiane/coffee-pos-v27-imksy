package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedInventoryUpdateEventApplicationDTO; 
 import ai.shreds.shared.SharedInventoryUpdateResponseApplicationDTO; 
 import ai.shreds.infrastructure.InfrastructureInventoryUpdateRepositoryPort; 
 import ai.shreds.infrastructure.InfrastructureProductRepositoryPort; 
 import ai.shreds.shared.SharedUUID; 
 import ai.shreds.shared.SharedIntegerValueObject; 
 import ai.shreds.shared.SharedStringValueObject; 
 import ai.shreds.shared.SharedTimestamp; 
 import lombok.RequiredArgsConstructor; 
 import lombok.extern.slf4j.Slf4j; 
 import org.springframework.stereotype.Service; 
  
 @Service 
 @RequiredArgsConstructor 
 @Slf4j 
 public class DomainInventoryUpdateService implements DomainInventoryUpdateServicePort { 
  
     private final InfrastructureInventoryUpdateRepositoryPort inventoryUpdateRepository; 
     private final InfrastructureProductRepositoryPort productRepository; 
     private final DomainInventoryValidationService validationService; 
  
     @Override 
     public SharedInventoryUpdateResponseApplicationDTO updateInventoryForSale(SharedInventoryUpdateEventApplicationDTO event) { 
         if (!validateInventoryChange(event)) { 
             throw new DomainInventoryUpdateException("Invalid inventory change for sale event"); 
         } 
         DomainProductEntity product; 
         try { 
             product = productRepository.findById(event.getProductId()); 
         } catch (Exception e) { 
             log.error("Error retrieving product: {}", e.getMessage()); 
             throw new DomainInventoryUpdateException("Error retrieving product"); 
         } 
         int newQuantity = product.getCurrentQuantity().getValue() - event.getQuantityChange().getValue(); 
         product.setCurrentQuantity(new SharedIntegerValueObject(newQuantity)); 
         product.setLastUpdated(new SharedTimestamp(System.currentTimeMillis())); 
         try { 
             productRepository.save(product); 
         } catch (Exception e) { 
             log.error("Error saving product: {}", e.getMessage()); 
             throw new DomainInventoryUpdateException("Error saving product"); 
         } 
         return generateConfirmationResponse(product); 
     } 
  
     @Override 
     public SharedInventoryUpdateResponseApplicationDTO updateInventoryForRestock(SharedInventoryUpdateEventApplicationDTO event) { 
         if (!validateInventoryChange(event)) { 
             throw new DomainInventoryUpdateException("Invalid inventory change for restock event"); 
         } 
         DomainProductEntity product; 
         try { 
             product = productRepository.findById(event.getProductId()); 
         } catch (Exception e) { 
             log.error("Error retrieving product: {}", e.getMessage()); 
             throw new DomainInventoryUpdateException("Error retrieving product"); 
         } 
         int newQuantity = product.getCurrentQuantity().getValue() + event.getQuantityChange().getValue(); 
         product.setCurrentQuantity(new SharedIntegerValueObject(newQuantity)); 
         product.setLastUpdated(new SharedTimestamp(System.currentTimeMillis())); 
         try { 
             productRepository.save(product); 
         } catch (Exception e) { 
             log.error("Error saving product: {}", e.getMessage()); 
             throw new DomainInventoryUpdateException("Error saving product"); 
         } 
         return generateConfirmationResponse(product); 
     } 
  
     @Override 
     public boolean validateInventoryChange(SharedInventoryUpdateEventApplicationDTO event) { 
         return validationService.isValidChange(event); 
     } 
  
     private SharedInventoryUpdateResponseApplicationDTO generateConfirmationResponse(DomainProductEntity product) { 
         return new SharedInventoryUpdateResponseApplicationDTO( 
                 product.getId(), 
                 product.getCurrentQuantity(), 
                 product.getLastUpdated() 
         ); 
     } 
 } 
 