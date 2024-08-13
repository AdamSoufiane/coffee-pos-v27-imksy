package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainInventoryEntity; 
 import ai.shreds.domain.DomainInventoryRepositoryPort; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Repository; 
 import javax.persistence.EntityManager; 
 import javax.persistence.PersistenceContext; 
 import javax.transaction.Transactional; 
 import java.util.UUID; 
 import lombok.RequiredArgsConstructor; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @Repository 
 @RequiredArgsConstructor 
 public class InfrastructureInventoryRepositoryImpl implements DomainInventoryRepositoryPort { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureInventoryRepositoryImpl.class); 
  
     @PersistenceContext 
     private final EntityManager entityManager; 
  
     /** 
      * Saves a new inventory entity to the database. 
      *  
      * @param entity the inventory entity to save 
      */ 
     @Override 
     @Transactional 
     public void save(DomainInventoryEntity entity) { 
         logger.info("Saving inventory entity: {}", entity); 
         entityManager.persist(entity); 
     } 
  
     /** 
      * Finds an inventory entity by its product ID. 
      *  
      * @param productId the unique identifier of the product 
      * @return the found inventory entity 
      */ 
     @Override 
     @Transactional 
     public DomainInventoryEntity findByProductId(UUID productId) { 
         try { 
             logger.info("Finding inventory by product ID: {}", productId); 
             return entityManager.createQuery("SELECT i FROM DomainInventoryEntity i WHERE i.productId = :productId", DomainInventoryEntity.class) 
                     .setParameter("productId", productId) 
                     .getSingleResult(); 
         } catch (Exception e) { 
             logger.error("Error finding inventory by product ID: {}", productId, e); 
             throw new InventoryNotFoundException("Error finding inventory by product ID", e); 
         } 
     } 
  
     /** 
      * Updates an existing inventory entity in the database. 
      *  
      * @param entity the inventory entity to update 
      */ 
     @Override 
     @Transactional 
     public void update(DomainInventoryEntity entity) { 
         logger.info("Updating inventory entity: {}", entity); 
         entityManager.merge(entity); 
     } 
 } 
  
 /** 
  * Custom exception for inventory not found scenarios. 
  */ 
 class InventoryNotFoundException extends RuntimeException { 
     public InventoryNotFoundException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 } 
 