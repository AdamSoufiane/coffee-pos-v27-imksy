package ai.shreds.domain; 
  
 import java.util.UUID; 
  
 /** 
  * DomainInventoryRepositoryPort provides an abstraction for all database operations related to the Inventory entity. 
  */ 
 public interface DomainInventoryRepositoryPort { 
     /** 
      * Saves the given inventory entity to the database. 
      *  
      * @param inventory the inventory entity to be saved 
      */ 
     void save(DomainInventoryEntity inventory); 
 } 
 