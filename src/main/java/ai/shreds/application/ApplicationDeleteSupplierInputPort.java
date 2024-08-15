package ai.shreds.application; 
  
 /** 
  * This interface defines the contract for deleting a supplier record in the database. 
  */ 
 @FunctionalInterface 
 public interface ApplicationDeleteSupplierInputPort { 
     /** 
      * Deletes a supplier record from the database based on the given ID. 
      * 
      * @param id the unique identifier of the supplier to be deleted 
      */ 
     void deleteSupplier(Long id); 
 }