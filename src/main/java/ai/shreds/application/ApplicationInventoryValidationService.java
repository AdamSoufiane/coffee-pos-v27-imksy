package ai.shreds.application; 
  
 import ai.shreds.shared.SharedInventoryUpdateEventApplicationDTO; 
 import ai.shreds.domain.DomainProductEntity; 
 import ai.shreds.domain.DomainInventoryUpdateServicePort; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
 import lombok.extern.slf4j.Slf4j; 
  
 @Slf4j 
 @Service 
 public class ApplicationInventoryValidationService { 
  
     private final DomainInventoryUpdateServicePort domainInventoryUpdateServicePort; 
  
     @Autowired 
     public ApplicationInventoryValidationService(DomainInventoryUpdateServicePort domainInventoryUpdateServicePort) { 
         this.domainInventoryUpdateServicePort = domainInventoryUpdateServicePort; 
     } 
  
     public boolean isValidChange(SharedInventoryUpdateEventApplicationDTO request) { 
         try { 
             // Validate request object 
             if (request == null || request.getId() == null || request.getProductId() == null || request.getQuantityChange() == null || request.getEventType() == null || request.getTimestamp() == null) { 
                 log.error("Invalid request: missing required fields"); 
                 return false; 
             } 
  
             // Validate event type 
             if (!"sale".equals(request.getEventType().getValue()) && !"restock".equals(request.getEventType().getValue())) { 
                 return false; 
             } 
  
             // Validate timestamp 
             if (request.getTimestamp().getTimestamp().isEmpty()) { 
                 log.error("Invalid timestamp"); 
                 return false; 
             } 
  
             // Fetch product details via port interface 
             DomainProductEntity product = domainInventoryUpdateServicePort.findProductById(request.getProductId()); 
             if (product == null) { 
                 return false; 
             } 
  
             // Calculate new quantity 
             int newQuantity = product.getCurrentQuantity().getValue() + request.getQuantityChange().getValue(); 
  
             // Ensure new quantity is not negative 
             if (newQuantity < 0) { 
                 return false; 
             } 
  
             // Validate large quantity changes 
             if (Math.abs(request.getQuantityChange().getValue()) > 100) { // Example threshold 
                 log.warn("Large quantity change detected: {}", request.getQuantityChange().getValue()); 
                 return false; 
             } 
  
             // Additional business rule validations can be added here 
  
             return true; 
         } catch (Exception e) { 
             log.error("Error validating inventory change", e); 
             return false; 
         } 
     } 
 } 
 