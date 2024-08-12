package ai.shreds.adapter; 
  
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.ControllerAdvice; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
  
 @ControllerAdvice 
 public class AdapterProductControllerException { 
  
     @ExceptionHandler(IllegalArgumentException.class) 
     public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) { 
         String errorMessage = "Invalid input data: " + e.getMessage(); 
         return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST); 
     } 
  
     @ExceptionHandler(IllegalStateException.class) 
     public ResponseEntity<String> handleIllegalStateException(IllegalStateException e) { 
         String errorMessage = "Internal server error: " + e.getMessage(); 
         return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR); 
     } 
  
     @ExceptionHandler(Exception.class) 
     public ResponseEntity<String> handleException(Exception e) { 
         String errorMessage = "Unexpected error: " + e.getMessage(); 
         return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR); 
     } 
 } 
 