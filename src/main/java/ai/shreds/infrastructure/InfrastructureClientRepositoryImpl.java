package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainClientEntity; 
 import ai.shreds.domain.DomainClientRepositoryPort; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Repository; 
 import org.springframework.transaction.annotation.Transactional; 
 import javax.persistence.EntityManager; 
 import javax.persistence.PersistenceContext; 
 import java.util.UUID; 
 import java.util.logging.Logger; 
  
 @Repository 
 public class InfrastructureClientRepositoryImpl implements DomainClientRepositoryPort { 
  
     private static final Logger LOGGER = Logger.getLogger(InfrastructureClientRepositoryImpl.class.getName()); 
  
     @PersistenceContext 
     private EntityManager entityManager; 
  
     @Override 
     @Transactional 
     public void save(DomainClientEntity client) { 
         if (client == null) { 
             throw new IllegalArgumentException("Client entity cannot be null"); 
         } 
         LOGGER.info("Saving client with ID: " + client.getClientId()); 
         entityManager.persist(client); 
     } 
  
     @Override 
     public DomainClientEntity findById(UUID id) { 
         if (id == null) { 
             throw new IllegalArgumentException("Client ID cannot be null"); 
         } 
         LOGGER.info("Finding client with ID: " + id); 
         return entityManager.find(DomainClientEntity.class, id); 
     } 
 } 
 // Note: Use Lombok annotations for getters and setters in DomainClientEntity.