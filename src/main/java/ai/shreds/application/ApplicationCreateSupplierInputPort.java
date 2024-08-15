package ai.shreds.application; 
  
 import ai.shreds.shared.ApplicationCreateSupplierDTO; 
 import ai.shreds.shared.ApplicationSupplierDTO; 
  
 /** 
  * Interface for creating a new supplier record. 
  */ 
 public interface ApplicationCreateSupplierInputPort { 
     /** 
      * Creates a new supplier. 
      *  
      * @param dto the supplier data transfer object 
      * @return the created supplier data transfer object 
      */ 
     ApplicationSupplierDTO createSupplier(ApplicationCreateSupplierDTO dto); 
 } 
 