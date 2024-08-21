package ai.shreds.domain; 
  
 import ai.shreds.shared.SharedInventoryUpdateEventApplicationDTO; 
 import ai.shreds.shared.SharedInventoryUpdateResponseApplicationDTO; 
  
 /** 
  * DomainInventoryUpdateServicePort defines the contract for updating inventory based on sales and restocking events as well as validating inventory changes. 
  */ 
 public interface DomainInventoryUpdateServicePort { 
  
     /** 
      * Processes an InventoryUpdateEvent with eventType 'sale' and updates the inventory accordingly. 
      * 
      * @param event SharedInventoryUpdateEventApplicationDTO containing the details of the sale event 
      * @return SharedInventoryUpdateResponseApplicationDTO containing the updated inventory details 
      */ 
     SharedInventoryUpdateResponseApplicationDTO updateInventoryForSale(SharedInventoryUpdateEventApplicationDTO event); 
  
     /** 
      * Processes an InventoryUpdateEvent with eventType 'restock' and updates the inventory accordingly. 
      * 
      * @param event SharedInventoryUpdateEventApplicationDTO containing the details of the restock event 
      * @return SharedInventoryUpdateResponseApplicationDTO containing the updated inventory details 
      */ 
     SharedInventoryUpdateResponseApplicationDTO updateInventoryForRestock(SharedInventoryUpdateEventApplicationDTO event); 
  
     /** 
      * Validates the inventory change to ensure it is within acceptable limits. 
      * 
      * @param event SharedInventoryUpdateEventApplicationDTO containing the details of the inventory change 
      * @return boolean indicating whether the inventory change is valid 
      */ 
     boolean validateInventoryChange(SharedInventoryUpdateEventApplicationDTO event); 
 } 
 