package ai.shreds.application; 
  
 import ai.shreds.adapter.AdapterCreateProductRequest; 
 import ai.shreds.adapter.AdapterProductResponse; 
 import ai.shreds.adapter.AdapterUpdateProductRequest; 
 import ai.shreds.domain.DomainProductEntity; 
 import ai.shreds.domain.DomainProductRepositoryPort; 
 import ai.shreds.domain.DomainProductService; 
 import ai.shreds.domain.DomainInventoryServicePort; 
 import ai.shreds.adapter.AdapterProductMapper; 
 import lombok.RequiredArgsConstructor; 
 import org.springframework.stereotype.Service; 
 import org.springframework.transaction.annotation.Transactional; 
 import java.util.UUID; 
  
 @Service 
 @RequiredArgsConstructor 
 public class ApplicationProductService implements ApplicationCreateProductInputPort, ApplicationUpdateProductInputPort, ApplicationDeleteProductInputPort { 
  
     private final DomainProductRepositoryPort domainProductRepositoryPort; 
     private final DomainInventoryServicePort domainInventoryServicePort; 
     private final AdapterProductMapper adapterProductMapper; 
  
     @Override 
     @Transactional 
     public AdapterProductResponse createProduct(AdapterCreateProductRequest request) { 
         validateCreateProductRequest(request); 
         try { 
             DomainProductEntity productEntity = adapterProductMapper.mapToDomainEntity(request); 
             domainProductRepositoryPort.save(productEntity); 
             domainInventoryServicePort.updateInventory(productEntity); 
             return adapterProductMapper.mapToAdapterResponse(productEntity); 
         } catch (Exception e) { 
             throw new ApplicationProductServiceException(e.getMessage(), e); 
         } 
     } 
  
     @Override 
     @Transactional 
     public AdapterProductResponse updateProduct(UUID id, AdapterUpdateProductRequest request) { 
         validateUpdateProductRequest(id, request); 
         try { 
             DomainProductEntity productEntity = adapterProductMapper.mapToDomainEntity(request); 
             domainProductRepositoryPort.save(productEntity); 
             domainInventoryServicePort.updateInventory(productEntity); 
             return adapterProductMapper.mapToAdapterResponse(productEntity); 
         } catch (Exception e) { 
             throw new ApplicationProductServiceException(e.getMessage(), e); 
         } 
     } 
  
     @Override 
     @Transactional 
     public void deleteProduct(UUID id) { 
         validateDeleteProductRequest(id); 
         try { 
             domainProductRepositoryPort.deleteById(id); 
             domainInventoryServicePort.updateInventory(null); // Assuming null indicates deletion 
         } catch (Exception e) { 
             throw new ApplicationProductServiceException(e.getMessage(), e); 
         } 
     } 
  
     private void validateCreateProductRequest(AdapterCreateProductRequest request) { 
         if (request.getName() == null || request.getName().isEmpty()) { 
             throw new InvalidProductDataException("Product name is required."); 
         } 
         if (request.getPrice() == null || request.getPrice().compareTo(BigDecimal.ZERO) <= 0) { 
             throw new InvalidProductDataException("Product price must be a positive value."); 
         } 
         if (request.getStockQuantity() == null || request.getStockQuantity() < 0) { 
             throw new InvalidProductDataException("Stock quantity must be a non-negative integer."); 
         } 
         if (request.getCategoryId() == null) { 
             throw new InvalidProductDataException("Category ID is required."); 
         } 
     } 
  
     private void validateUpdateProductRequest(UUID id, AdapterUpdateProductRequest request) { 
         if (id == null) { 
             throw new InvalidProductDataException("Product ID is required."); 
         } 
         validateCreateProductRequest(request); 
     } 
  
     private void validateDeleteProductRequest(UUID id) { 
         if (id == null) { 
             throw new InvalidProductDataException("Product ID is required."); 
         } 
     } 
 } 
  
 class ApplicationProductServiceException extends RuntimeException { 
     public ApplicationProductServiceException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 } 
  
 class InvalidProductDataException extends RuntimeException { 
     public InvalidProductDataException(String message) { 
         super(message); 
     } 
 }