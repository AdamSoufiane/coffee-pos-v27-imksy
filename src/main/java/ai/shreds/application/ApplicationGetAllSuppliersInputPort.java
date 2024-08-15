package ai.shreds.application; 
  
 import ai.shreds.shared.ApplicationSupplierDTO; 
 import java.util.List; 
  
 /** 
  * ApplicationGetAllSuppliersInputPort defines a contract for retrieving all supplier records. 
  * This interface serves as an abstraction layer between the controller (adapter) and the domain logic. 
  */ 
 @FunctionalInterface 
 public interface ApplicationGetAllSuppliersInputPort { 
     List<ApplicationSupplierDTO> getAllSuppliers(); 
 }