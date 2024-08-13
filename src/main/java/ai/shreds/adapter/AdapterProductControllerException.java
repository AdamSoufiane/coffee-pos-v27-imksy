package ai.shreds.adapter; 
  
 import ai.shreds.shared.AdapterProductResponse; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.web.bind.annotation.RestControllerAdvice; 
  
 /** 
  * Exception handler for AdapterProductController. 
  * This class handles various exceptions that might occur in the controller 
  * and maps them to appropriate HTTP responses. 
  */ 
 @RestControllerAdvice 
 public class AdapterProductControllerException { 
  
     private static final Logger logger = LoggerFactory.getLogger(AdapterProductControllerException.class); 
  
     /** 
      * Handles generic exceptions. 
      *  
      * @param exception the exception thrown 
      * @return a ResponseEntity containing the error message and HTTP status 
      */ 
     @ExceptionHandler(Exception.class) 
     public ResponseEntity<AdapterProductResponse> handleException(Exception exception) { 
         AdapterProductResponse response = new AdapterProductResponse(); 
         if (exception instanceof IllegalArgumentException) { 
             response.setMessage("Invalid product data: " + exception.getMessage()); 
             logger.error("IllegalArgumentException: {}", exception.getMessage()); 
             return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); 
         } else if (exception instanceof NullPointerException) { 
             response.setMessage("A required value was null: " + exception.getMessage()); 
             logger.error("NullPointerException: {}", exception.getMessage()); 
             return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); 
         } else if (exception instanceof IllegalStateException) { 
             response.setMessage("Illegal state: " + exception.getMessage()); 
             logger.error("IllegalStateException: {}", exception.getMessage()); 
             return new ResponseEntity<>(response, HttpStatus.CONFLICT); 
         } else { 
             response.setMessage("An unexpected error occurred while processing your request."); 
             logger.error("Exception: {}", exception.getMessage()); 
             return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); 
         } 
     } 
 } 
 