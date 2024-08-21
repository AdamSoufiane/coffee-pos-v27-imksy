package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainInventoryUpdateEventEntity; 
 import ai.shreds.shared.SharedUUID; 
 import org.springframework.data.jpa.repository.JpaRepository; 
 import org.springframework.stereotype.Repository; 
  
 import java.util.List; 
  
 /** 
  * Repository interface for CRUD operations on InventoryUpdateEvent entities. 
  */ 
 @Repository 
 public interface InfrastructureInventoryUpdateRepositoryPort extends JpaRepository<DomainInventoryUpdateEventEntity, SharedUUID> { 
     /** 
      * Finds all InventoryUpdateEvent entities by productId. 
      * 
      * @param productId the ID of the product 
      * @return a list of InventoryUpdateEvent entities 
      */ 
     List<DomainInventoryUpdateEventEntity> findAllByProductId(SharedUUID productId); 
  
     /** 
      * Finds all InventoryUpdateEvent entities by eventType. 
      * 
      * @param eventType the type of the event 
      * @return a list of InventoryUpdateEvent entities 
      */ 
     List<DomainInventoryUpdateEventEntity> findAllByEventType(String eventType); 
 } 
 