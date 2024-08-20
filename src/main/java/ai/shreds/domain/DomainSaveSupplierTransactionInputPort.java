package ai.shreds.domain; 
  
 import java.util.UUID; 
  
 /** 
  * Interface for saving supplier transactions. 
  * This interface will be implemented by classes in the infrastructure layer to provide the actual saving functionality. 
  */ 
 public interface DomainSaveSupplierTransactionInputPort { 
     void save(DomainSupplierTransaction supplierTransaction); 
 } 
  
 // Implementation Note: Consider using Lombok annotations for getters and setters in DomainSupplierTransaction. 
 // Implementation Note: Consider using Lombok annotations for getters and setters in DomainProductTransaction. 
 // Implementation Note: Consider using Lombok annotations for constructors in DomainSupplierTransaction. 
 