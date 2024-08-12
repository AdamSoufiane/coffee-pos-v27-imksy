package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainInventoryEntity; 
 import ai.shreds.domain.DomainInventoryRepositoryPort; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Repository; 
 import javax.persistence.EntityManager; 
 import javax.persistence.PersistenceContext; 
 import javax.transaction.Transactional; 
 import java.util.UUID; 
 import lombok.extern.slf4j.Slf4j; 
 import lombok.Getter; 
 import lombok.Setter; 
  
 @Repository 
 @Slf4j 
 public class InfrastructureInventoryRepositoryImpl implements DomainInventoryRepositoryPort { 
  
     @PersistenceContext 
     private EntityManager entityManager; 
  
     @Override 
     public DomainInventoryEntity findByProductId(UUID productId) { 
         try { 
             return entityManager.createQuery("SELECT i FROM DomainInventoryEntity i WHERE i.productId = :productId", DomainInventoryEntity.class) 
                     .setParameter("productId", productId) 
                     .getSingleResult(); 
         } catch (Exception e) { 
             log.error("Error finding inventory by product ID", e); 
             throw new RuntimeException("Error finding inventory by product ID", e); 
         } 
     } 
  
     @Override 
     @Transactional 
     public void save(DomainInventoryEntity inventory) { 
         try { 
             if (inventory.getId() == null) { 
                 entityManager.persist(inventory); 
             } else { 
                 entityManager.merge(inventory); 
             } 
         } catch (Exception e) { 
             log.error("Error saving inventory", e); 
             throw new RuntimeException("Error saving inventory", e); 
         } 
     } 
 } 
 