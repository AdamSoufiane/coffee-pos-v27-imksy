package ai.shreds.infrastructure; 
  
 /** 
  * Exception class for handling exceptions specific to the InventoryUpdateRepository implementation. 
  */ 
 public class InfrastructureInventoryUpdateRepositoryImplException extends Exception { 
     private static final long serialVersionUID = 1L; 
  
     /** 
      * Constructs a new InfrastructureInventoryUpdateRepositoryImplException with the specified detail message. 
      *  
      * @param message the detail message. 
      */ 
     public InfrastructureInventoryUpdateRepositoryImplException(String message) { 
         super(message); 
     } 
 }