package ai.shreds.domain; 
  
 import java.util.UUID; 
  
 /** 
  * Interface for saving a product transaction. 
  */ 
 public interface DomainSaveProductTransactionInputPort { 
     /** 
      * Saves a product transaction to the database. 
      *  
      * @param productTransaction the product transaction to save 
      */ 
     void save(DomainProductTransaction productTransaction); 
 } 
 