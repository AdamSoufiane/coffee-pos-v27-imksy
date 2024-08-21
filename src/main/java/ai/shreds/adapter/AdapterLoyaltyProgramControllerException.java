package ai.shreds.adapter; 
  
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.ControllerAdvice; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
  
 @ControllerAdvice 
 public class AdapterLoyaltyProgramControllerException { 
  
     private static final Logger logger = LoggerFactory.getLogger(AdapterLoyaltyProgramControllerException.class); 
  
     @ExceptionHandler(Throwable.class) 
     public ResponseEntity<String> handleException(Throwable e) { 
         logger.error("Exception occurred: {}", e.getClass().getName(), e); 
         return new ResponseEntity<>("An error occurred: " + e.getMessage() + ". Exception type: " + e.getClass().getName(), HttpStatus.INTERNAL_SERVER_ERROR); 
     } 
 } 
 