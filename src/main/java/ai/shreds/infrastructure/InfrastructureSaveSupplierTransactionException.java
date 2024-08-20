package ai.shreds.infrastructure; 
  
 /** 
  * Exception thrown when there is an error saving a SupplierTransaction in the infrastructure layer. 
  */ 
 public class InfrastructureSaveSupplierTransactionException extends Exception { 
  
     public InfrastructureSaveSupplierTransactionException() { 
         super(); 
     } 
  
     public InfrastructureSaveSupplierTransactionException(String message) { 
         super(message); 
     } 
  
     public InfrastructureSaveSupplierTransactionException(String message, Throwable cause) { 
         super(message, cause); 
     } 
  
     public InfrastructureSaveSupplierTransactionException(Throwable cause) { 
         super(cause); 
     } 
  
     protected InfrastructureSaveSupplierTransactionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) { 
         super(message, cause, enableSuppression, writableStackTrace); 
     } 
 } 
 