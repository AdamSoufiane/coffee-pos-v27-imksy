package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainInventoryUpdateEventEntity; 
 import ai.shreds.shared.SharedUUID; 
 import org.springframework.stereotype.Repository; 
 import javax.persistence.EntityManager; 
 import javax.persistence.PersistenceContext; 
 import javax.transaction.Transactional; 
  
 @Repository 
 @Transactional 
 public class InfrastructureSQLInventoryUpdateRepositoryImpl implements InfrastructureInventoryUpdateRepositoryPort { 
  
     @PersistenceContext 
     private EntityManager entityManager; 
  
     @Override 
     public void save(DomainInventoryUpdateEventEntity event) { 
         try { 
             entityManager.persist(event); 
         } catch (Exception e) { 
             throw new InfrastructureInventoryUpdateRepositoryImplException("Error saving InventoryUpdateEvent: " + e.getMessage()); 
         } 
     } 
  
     @Override 
     public DomainInventoryUpdateEventEntity findById(SharedUUID id) { 
         try { 
             return entityManager.find(DomainInventoryUpdateEventEntity.class, id.getUuid()); 
         } catch (Exception e) { 
             throw new InfrastructureInventoryUpdateRepositoryImplException("Error finding InventoryUpdateEvent by ID: " + e.getMessage()); 
         } 
     } 
 } 
  
 class InfrastructureInventoryUpdateRepositoryImplException extends RuntimeException { 
     public InfrastructureInventoryUpdateRepositoryImplException(String message) { 
         super(message); 
     } 
 } 
  
 // Note: Use Lombok annotations for boilerplate code like getters and setters.