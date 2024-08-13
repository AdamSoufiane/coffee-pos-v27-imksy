package ai.shreds.domain; 
  
 import ai.shreds.domain.DomainInventoryRepositoryPort; 
 import ai.shreds.domain.DomainProductEntity; 
 import ai.shreds.domain.DomainInventoryEntity; 
 import java.util.UUID; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
 import lombok.extern.slf4j.Slf4j; 
 import lombok.RequiredArgsConstructor; 
  
 @Slf4j 
 @RequiredArgsConstructor 
 @Service 
 public class DomainInventoryService { 
  
     private final DomainInventoryRepositoryPort inventoryRepository; 
  
     public void updateInventoryDetails(DomainProductEntity product) { 
         // Validate product data (assuming validation logic is implemented elsewhere) 
         validateProductData(product); 
  
         // Retrieve current inventory details 
         try { 
             DomainInventoryEntity currentInventory = inventoryRepository.findByProductId(product.getId()); 
  
             // Update inventory details based on product changes 
             currentInventory.setAvailableQuantity(product.getStockQuantity()); 
             currentInventory.setLastChecked(new java.sql.Timestamp(System.currentTimeMillis())); 
  
             // Save updated inventory details 
             inventoryRepository.update(currentInventory); 
             log.info("Updated inventory details for product ID: {}", product.getId()); 
         } catch (Exception e) { 
             log.error("Error updating inventory details for product ID: {}", product.getId(), e); 
             throw new InventoryUpdateException("Failed to update inventory details", e); 
         } 
     } 
  
     public DomainInventoryEntity getInventoryDetailsForProduct(UUID id) { 
         // Validate product ID (assuming validation logic is implemented elsewhere) 
         validateProductId(id); 
  
         // Retrieve inventory details 
         try { 
             DomainInventoryEntity inventory = inventoryRepository.findByProductId(id); 
             log.info("Retrieved inventory details for product ID: {}", id); 
             return inventory; 
         } catch (Exception e) { 
             log.error("Error retrieving inventory details for product ID: {}", id, e); 
             throw new InventoryRetrievalException("Failed to retrieve inventory details", e); 
         } 
     } 
  
     private void validateProductData(DomainProductEntity product) { 
         // Implement validation logic here 
         if (product == null || product.getId() == null) { 
             throw new IllegalArgumentException("Invalid product data"); 
         } 
     } 
  
     private void validateProductId(UUID id) { 
         // Implement validation logic here 
         if (id == null) { 
             throw new IllegalArgumentException("Invalid product ID"); 
         } 
     } 
 } 
  
 class InventoryUpdateException extends RuntimeException { 
     public InventoryUpdateException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 } 
  
 class InventoryRetrievalException extends RuntimeException { 
     public InventoryRetrievalException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 } 
 