package ai.shreds.adapter; 
  
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.web.bind.annotation.RestControllerAdvice; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @RestControllerAdvice 
 public class AdapterSupplierControllerException { 
  
     private static final Logger logger = LoggerFactory.getLogger(AdapterSupplierControllerException.class); 
  
     @ExceptionHandler(Exception.class) 
     public ResponseEntity<String> handleException(Exception e) { 
         logger.error("Exception caught: ", e); 
         if (e instanceof DataNotFoundException) { 
             return new ResponseEntity<>("Data not found: " + e.getMessage(), HttpStatus.NOT_FOUND); 
         } else if (e instanceof InvalidInputException) { 
             return new ResponseEntity<>("Invalid input: " + e.getMessage(), HttpStatus.BAD_REQUEST); 
         } else { 
             return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
         } 
     } 
 } 
  
 class DataNotFoundException extends RuntimeException { 
     public DataNotFoundException(String message) { 
         super(message); 
     } 
 } 
  
 class InvalidInputException extends RuntimeException { 
     public InvalidInputException(String message) { 
         super(message); 
     } 
 } 
  
 class DatabaseException extends RuntimeException { 
     public DatabaseException(String message) { 
         super(message); 
     } 
 } 
  
 class ServiceException extends RuntimeException { 
     public ServiceException(String message) { 
         super(message); 
     } 
 }