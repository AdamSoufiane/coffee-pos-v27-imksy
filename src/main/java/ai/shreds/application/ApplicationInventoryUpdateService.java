package ai.shreds.application; 
  
 import ai.shreds.shared.SharedInventoryUpdateEventApplicationDTO; 
 import ai.shreds.shared.SharedInventoryUpdateResponseApplicationDTO; 
 import ai.shreds.domain.DomainInventoryUpdateServicePort; 
 import lombok.RequiredArgsConstructor; 
 import org.springframework.stereotype.Service; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @Service 
 @RequiredArgsConstructor 
 public class ApplicationInventoryUpdateService implements ApplicationInventoryUpdateServicePort { 
     private final DomainInventoryUpdateServicePort domainUpdateInventoryPort; 
     private final ApplicationInventoryValidationService validationService; 
     private static final Logger logger = LoggerFactory.getLogger(ApplicationInventoryUpdateService.class); 
  
     /** 
      * Updates inventory for a sale event. 
      * @param request the inventory update event data 
      * @return the response containing updated inventory details 
      */ 
     @Override 
     public SharedInventoryUpdateResponseApplicationDTO updateInventoryForSale(SharedInventoryUpdateEventApplicationDTO request) { 
         if (request == null) { 
             throw new IllegalArgumentException("Request cannot be null"); 
         } 
         logger.info("Processing sale event for product: {}", request.getProductId()); 
         if (validateInventoryChange(request)) { 
             return domainUpdateInventoryPort.updateInventoryForSale(request); 
         } else { 
             throw new ApplicationInventoryUpdateException("Invalid inventory change for sale event"); 
         } 
     } 
  
     /** 
      * Updates inventory for a restock event. 
      * @param request the inventory update event data 
      * @return the response containing updated inventory details 
      */ 
     @Override 
     public SharedInventoryUpdateResponseApplicationDTO updateInventoryForRestock(SharedInventoryUpdateEventApplicationDTO request) { 
         if (request == null) { 
             throw new IllegalArgumentException("Request cannot be null"); 
         } 
         logger.info("Processing restock event for product: {}", request.getProductId()); 
         if (validateInventoryChange(request)) { 
             return domainUpdateInventoryPort.updateInventoryForRestock(request); 
         } else { 
             throw new ApplicationInventoryUpdateException("Invalid inventory change for restock event"); 
         } 
     } 
  
     /** 
      * Validates the inventory change. 
      * @param request the inventory update event data 
      * @return true if the change is valid, false otherwise 
      */ 
     @Override 
     public Boolean validateInventoryChange(SharedInventoryUpdateEventApplicationDTO request) { 
         if (request == null) { 
             throw new IllegalArgumentException("Request cannot be null"); 
         } 
         return validationService.isValidChange(request); 
     } 
 } 
 