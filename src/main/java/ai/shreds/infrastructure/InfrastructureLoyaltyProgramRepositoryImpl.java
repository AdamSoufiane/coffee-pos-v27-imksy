package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainLoyaltyProgramEntity; 
 import ai.shreds.domain.DomainLoyaltyProgramRepositoryPort; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Repository; 
 import org.springframework.transaction.annotation.Transactional; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 import javax.persistence.EntityManager; 
 import javax.persistence.PersistenceContext; 
 import java.util.UUID; 
  
 @Repository 
 public class InfrastructureLoyaltyProgramRepositoryImpl implements DomainLoyaltyProgramRepositoryPort { 
  
     @PersistenceContext 
     private EntityManager entityManager; 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureLoyaltyProgramRepositoryImpl.class); 
  
     @Override 
     @Transactional 
     public void save(DomainLoyaltyProgramEntity loyaltyProgram) { 
         try { 
             logger.info("Saving loyalty program: {}", loyaltyProgram); 
             entityManager.persist(loyaltyProgram); 
         } catch (Exception e) { 
             logger.error("Error saving loyalty program: {}", e.getMessage()); 
             throw new RuntimeException("Failed to save loyalty program", e); 
         } 
     } 
  
     @Override 
     public DomainLoyaltyProgramEntity findById(UUID id) { 
         try { 
             logger.info("Finding loyalty program by ID: {}", id); 
             return entityManager.find(DomainLoyaltyProgramEntity.class, id); 
         } catch (Exception e) { 
             logger.error("Error finding loyalty program by ID: {}", e.getMessage()); 
             throw new RuntimeException("Failed to find loyalty program", e); 
         } 
     } 
 }