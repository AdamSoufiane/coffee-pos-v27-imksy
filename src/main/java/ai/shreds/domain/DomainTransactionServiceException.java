package ai.shreds.domain; 
  
 /** 
  * Custom exception class for handling errors related to transaction services within the domain layer. 
  * This exception is thrown by methods in the DomainTransactionService class when they encounter 
  * errors related to saving supplier transactions or calculating total amounts. 
  */ 
 public class DomainTransactionServiceException extends Exception { 
  
     public DomainTransactionServiceException() { 
         super(); 
     } 
  
     public DomainTransactionServiceException(String message) { 
         super(message); 
     } 
  
     public DomainTransactionServiceException(String message, Throwable cause) { 
         super(message, cause); 
     } 
  
     public DomainTransactionServiceException(Throwable cause) { 
         super(cause); 
     } 
 } 
 