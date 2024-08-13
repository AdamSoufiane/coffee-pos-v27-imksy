package ai.shreds.infrastructure; 
  
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.dao.DataAccessException; 
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.ControllerAdvice; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.web.bind.annotation.ResponseStatus; 
  
 import java.time.LocalDateTime; 
 import java.util.HashMap; 
 import java.util.Map; 
  
 @ControllerAdvice 
 public class InfrastructureException { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureException.class); 
  
     @ExceptionHandler(Exception.class) 
     @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) 
     public ResponseEntity<Map<String, Object>> handleException(Exception exception) { 
         // Log the exception details 
         logger.error("An error occurred: ", exception); 
  
         // Create a standardized error response 
         Map<String, Object> errorResponse = new HashMap<>(); 
         errorResponse.put("message", "An unexpected error occurred while processing your request."); 
         errorResponse.put("timestamp", LocalDateTime.now()); 
         errorResponse.put("errorCode", "ERR-001"); 
  
         // Return the error response 
         return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); 
     } 
  
     @ExceptionHandler(DataAccessException.class) 
     @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) 
     public ResponseEntity<Map<String, Object>> handleDataAccessException(DataAccessException exception) { 
         // Log the exception details 
         logger.error("A data access error occurred: ", exception); 
  
         // Create a standardized error response 
         Map<String, Object> errorResponse = new HashMap<>(); 
         errorResponse.put("message", "A database error occurred while processing your request."); 
         errorResponse.put("timestamp", LocalDateTime.now()); 
         errorResponse.put("errorCode", "ERR-002"); 
  
         // Return the error response 
         return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR); 
     } 
 } 
 