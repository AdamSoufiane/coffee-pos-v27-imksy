package ai.shreds.application; 
  
 import ai.shreds.shared.SharedInventoryUpdateEventApplicationDTO; 
 import ai.shreds.shared.SharedInventoryUpdateResponseApplicationDTO; 
 import org.springframework.transaction.annotation.Transactional; 
  
 /** 
  * Interface for handling inventory updates. 
  */ 
 public interface ApplicationInventoryUpdateServicePort { 
  
     /** 
      * Processes an InventoryUpdateEvent with eventType 'sale' and updates the inventory accordingly. 
      *  
      * @param request the inventory update event data 
      * @return the updated inventory response 
      */ 
     @Transactional 
     SharedInventoryUpdateResponseApplicationDTO updateInventoryForSale(SharedInventoryUpdateEventApplicationDTO request); 
  
     /** 
      * Processes an InventoryUpdateEvent with eventType 'restock' and updates the inventory accordingly. 
      *  
      * @param request the inventory update event data 
      * @return the updated inventory response 
      */ 
     @Transactional 
     SharedInventoryUpdateResponseApplicationDTO updateInventoryForRestock(SharedInventoryUpdateEventApplicationDTO request); 
  
     /** 
      * Validates the inventory change to ensure it is within acceptable limits. 
      *  
      * @param request the inventory update event data 
      * @return true if the inventory change is valid, false otherwise 
      */ 
     boolean validateInventoryChange(SharedInventoryUpdateEventApplicationDTO request); 
 }