package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainProductEntity; 
 import ai.shreds.shared.SharedUUID; 
 import ai.shreds.shared.SharedIntegerValueObject; 
 import ai.shreds.shared.SharedStringValueObject; 
 import ai.shreds.shared.SharedTimestamp; 
 import org.springframework.stereotype.Repository; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.transaction.annotation.Transactional; 
  
 import javax.persistence.EntityManager; 
 import javax.persistence.PersistenceContext; 
 import javax.persistence.TypedQuery; 
 import java.util.Optional; 
 import lombok.extern.slf4j.Slf4j; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import lombok.AllArgsConstructor; 
  
 @Repository 
 @Slf4j 
 public class InfrastructureSQLProductRepositoryImpl implements InfrastructureProductRepositoryPort { 
  
     @PersistenceContext 
     private EntityManager entityManager; 
  
     @Override 
     @Transactional 
     public void save(DomainProductEntity product) { 
         try { 
             entityManager.persist(product); 
             log.info("Product saved: {}", product); 
         } catch (Exception e) { 
             log.error("Error saving product: {}", e.getMessage()); 
             throw new InfrastructureProductRepositoryImplException("Error saving product: " + e.getMessage()); 
         } 
     } 
  
     @Override 
     public Optional<DomainProductEntity> findById(SharedUUID id) { 
         try { 
             DomainProductEntity product = entityManager.find(DomainProductEntity.class, id); 
             return Optional.ofNullable(product); 
         } catch (Exception e) { 
             log.error("Error finding product by ID: {}", e.getMessage()); 
             throw new InfrastructureProductRepositoryImplException("Error finding product by ID: " + e.getMessage()); 
         } 
     } 
  
     @Override 
     @Transactional 
     public void updateQuantity(SharedUUID productId, SharedIntegerValueObject quantity) { 
         try { 
             Optional<DomainProductEntity> productOpt = findById(productId); 
             if (productOpt.isPresent()) { 
                 DomainProductEntity product = productOpt.get(); 
                 product.setCurrentQuantity(quantity); 
                 product.setLastUpdated(new SharedTimestamp(System.currentTimeMillis())); 
                 entityManager.merge(product); 
                 log.info("Product quantity updated: {}", product); 
             } else { 
                 log.warn("Product not found: {}", productId); 
                 throw new InfrastructureProductRepositoryImplException("Product not found"); 
             } 
         } catch (Exception e) { 
             log.error("Error updating product quantity: {}", e.getMessage()); 
             throw new InfrastructureProductRepositoryImplException("Error updating product quantity: " + e.getMessage()); 
         } 
     } 
  
     @Transactional 
     public void handleOptimisticLocking(SharedUUID productId, SharedIntegerValueObject quantity) { 
         try { 
             Optional<DomainProductEntity> productOpt = findById(productId); 
             if (productOpt.isPresent()) { 
                 DomainProductEntity product = productOpt.get(); 
                 product.setCurrentQuantity(quantity); 
                 product.setLastUpdated(new SharedTimestamp(System.currentTimeMillis())); 
                 entityManager.lock(product, LockModeType.OPTIMISTIC); 
                 entityManager.merge(product); 
                 log.info("Product quantity updated with optimistic locking: {}", product); 
             } else { 
                 log.warn("Product not found: {}", productId); 
                 throw new InfrastructureProductRepositoryImplException("Product not found"); 
             } 
         } catch (OptimisticLockException ole) { 
             log.error("Optimistic lock exception: {}", ole.getMessage()); 
             throw new InfrastructureProductRepositoryImplException("Optimistic lock exception: " + ole.getMessage()); 
         } catch (Exception e) { 
             log.error("Error updating product quantity with optimistic locking: {}", e.getMessage()); 
             throw new InfrastructureProductRepositoryImplException("Error updating product quantity with optimistic locking: " + e.getMessage()); 
         } 
     } 
  
     @Transactional 
     public void validateLargeQuantityChange(SharedUUID productId, SharedIntegerValueObject quantity) { 
         try { 
             Optional<DomainProductEntity> productOpt = findById(productId); 
             if (productOpt.isPresent()) { 
                 DomainProductEntity product = productOpt.get(); 
                 if (Math.abs(quantity.getValue()) > 1000) { // Example threshold 
                     log.warn("Unusually large quantity change detected for product {}: {}", productId, quantity); 
                     throw new InfrastructureProductRepositoryImplException("Unusually large quantity change detected"); 
                 } 
             } else { 
                 log.warn("Product not found: {}", productId); 
                 throw new InfrastructureProductRepositoryImplException("Product not found"); 
             } 
         } catch (Exception e) { 
             log.error("Error validating large quantity change: {}", e.getMessage()); 
             throw new InfrastructureProductRepositoryImplException("Error validating large quantity change: " + e.getMessage()); 
         } 
     } 
 } 
  
 class InfrastructureProductRepositoryImplException extends RuntimeException { 
     public InfrastructureProductRepositoryImplException(String message) { 
         super(message); 
     } 
 } 
  
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 class DomainProductEntity { 
     private SharedUUID id; 
     private SharedStringValueObject name; 
     private SharedIntegerValueObject currentQuantity; 
     private SharedTimestamp lastUpdated; 
 }