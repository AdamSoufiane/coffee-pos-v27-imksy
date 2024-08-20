package ai.shreds.infrastructure; 
  
 public class InfrastructureSaveProductTransactionException extends Exception { 
     public InfrastructureSaveProductTransactionException(String message) { 
         super(message); 
     } 
  
     public InfrastructureSaveProductTransactionException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 }