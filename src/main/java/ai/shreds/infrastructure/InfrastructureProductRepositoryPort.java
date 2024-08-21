package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainProductEntity; 
 import ai.shreds.shared.SharedUUID; 
 import ai.shreds.shared.SharedIntegerValueObject; 
 import org.springframework.stereotype.Repository; 
  
 /** 
  * Repository interface for handling CRUD operations related to Product entities. 
  */ 
 @Repository 
 public interface InfrastructureProductRepositoryPort { 
     /** 
      * Saves a product entity to the database. 
      *  
      * @param product the product entity to save 
      */ 
     void save(DomainProductEntity product); 
  
     /** 
      * Finds a product entity by its unique identifier. 
      *  
      * @param id the unique identifier of the product 
      * @return the found product entity 
      */ 
     DomainProductEntity findById(SharedUUID id); 
  
     /** 
      * Updates the quantity of a specific product entity. 
      *  
      * @param productId the unique identifier of the product 
      * @param quantity the new quantity value 
      */ 
     void updateQuantity(SharedUUID productId, SharedIntegerValueObject quantity); 
 }