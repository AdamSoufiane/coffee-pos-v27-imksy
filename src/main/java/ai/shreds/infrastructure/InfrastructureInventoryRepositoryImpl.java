package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainInventoryEntity; 
 import ai.shreds.domain.DomainInventoryRepositoryPort; 
 import lombok.RequiredArgsConstructor; 
 import org.springframework.stereotype.Repository; 
 import javax.persistence.EntityManager; 
 import javax.transaction.Transactional; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @Repository 
 @RequiredArgsConstructor 
 public class InfrastructureInventoryRepositoryImpl implements DomainInventoryRepositoryPort { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureInventoryRepositoryImpl.class); 
  
     private final EntityManager entityManager; 
  
     @Override 
     @Transactional 
     public void save(DomainInventoryEntity inventory) { 
         try { 
             entityManager.persist(inventory); 
             logger.info("Inventory saved successfully: {}", inventory); 
         } catch (Exception e) { 
             logger.error("Error saving inventory: {}", inventory, e); 
             throw e; 
         } 
     } 
 } 
 