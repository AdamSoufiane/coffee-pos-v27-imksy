package ai.shreds.infrastructure; 
  
 /** 
  * Custom exception class for handling infrastructure-related exceptions. 
  */ 
 public class InfrastructureException extends Exception { 
     private static final long serialVersionUID = 1L; 
  
     /** 
      * Constructs a new InfrastructureException with the specified detail message. 
      *  
      * @param message the detail message. 
      */ 
     public InfrastructureException(String message) { 
         super(message); 
     } 
  
     /** 
      * Constructs a new InfrastructureException with the specified detail message and cause. 
      *  
      * @param message the detail message. 
      * @param cause the cause of the exception. 
      */ 
     public InfrastructureException(String message, Throwable cause) { 
         super(message, cause); 
     } 
  
     /** 
      * Constructs a new InfrastructureException with the specified cause. 
      *  
      * @param cause the cause of the exception. 
      */ 
     public InfrastructureException(Throwable cause) { 
         super(cause); 
     } 
 }