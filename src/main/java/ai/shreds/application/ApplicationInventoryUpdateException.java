package ai.shreds.application; 
  
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 /** 
  * Custom exception class for handling inventory update exceptions in the application layer. 
  */ 
 public class ApplicationInventoryUpdateException extends Exception { 
     private static final Logger logger = LoggerFactory.getLogger(ApplicationInventoryUpdateException.class); 
  
     /** 
      * Constructs a new ApplicationInventoryUpdateException with the specified detail message. 
      * 
      * @param message the detail message 
      */ 
     public ApplicationInventoryUpdateException(String message) { 
         super(message); 
     } 
  
     /** 
      * Handles the exception by logging the error message. 
      */ 
     public void handleException() { 
         // Log the error message 
         logger.error("Inventory Update Exception: " + getMessage()); 
         // Additional handling logic can be added here 
     } 
 }