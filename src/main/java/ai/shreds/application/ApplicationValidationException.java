package ai.shreds.application; 
  
 import org.springframework.http.HttpStatus; 
 import org.springframework.web.bind.annotation.ResponseStatus; 
  
 /** 
  * Exception thrown when validation fails in the application layer. 
  * This exception is mapped to a BAD_REQUEST HTTP status code. 
  */ 
 @ResponseStatus(HttpStatus.BAD_REQUEST) 
 public class ApplicationValidationException extends Exception { 
  
     public ApplicationValidationException(String message) { 
         super(message); 
     } 
 }