package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainRepository; 
 import ai.shreds.domain.DomainSupplierTransaction; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Repository; 
 import lombok.RequiredArgsConstructor; 
  
 import javax.persistence.EntityManager; 
 import javax.persistence.PersistenceContext; 
 import javax.transaction.Transactional; 
 import java.util.UUID; 
  
 @Repository 
 @RequiredArgsConstructor 
 public class InfrastructureRepository implements DomainRepository { 
  
     @PersistenceContext 
     private final EntityManager entityManager; 
  
     @Override 
     @Transactional 
     public void save(DomainSupplierTransaction supplierTransaction) { 
         try { 
             entityManager.persist(supplierTransaction); 
             supplierTransaction.getProducts().forEach(entityManager::persist); 
         } catch (Exception e) { 
             throw new InfrastructureSaveSupplierTransactionException("Failed to save SupplierTransaction", e); 
         } 
     } 
  
     @Override 
     public DomainSupplierTransaction findById(UUID id) { 
         try { 
             return entityManager.find(DomainSupplierTransaction.class, id); 
         } catch (Exception e) { 
             throw new InfrastructureDatabaseException("Failed to find SupplierTransaction by ID", e); 
         } 
     } 
 } 
  
 class InfrastructureSaveSupplierTransactionException extends RuntimeException { 
     public InfrastructureSaveSupplierTransactionException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 } 
  
 class InfrastructureDatabaseException extends RuntimeException { 
     public InfrastructureDatabaseException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 }