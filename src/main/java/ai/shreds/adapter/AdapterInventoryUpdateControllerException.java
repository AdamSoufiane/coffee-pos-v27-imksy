package ai.shreds.adapter; 
  
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.http.HttpStatus; 
 import org.springframework.http.ResponseEntity; 
 import org.springframework.web.bind.annotation.ControllerAdvice; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import ai.shreds.application.ApplicationInventoryUpdateException; 
 import ai.shreds.infrastructure.InfrastructureInventoryUpdateRepositoryImplException; 
 import ai.shreds.infrastructure.InfrastructureProductRepositoryImplException; 
  
 @ControllerAdvice 
 public class AdapterInventoryUpdateControllerException { 
  
     private static final Logger logger = LoggerFactory.getLogger(AdapterInventoryUpdateControllerException.class); 
  
     @ExceptionHandler(Exception.class) 
     public ResponseEntity<Object> handleException(Exception e) { 
         logger.error("Exception occurred: ", e); 
         if (e instanceof ApplicationInventoryUpdateException) { 
             return new ResponseEntity<>(new ErrorResponse("Application error", e.getMessage()), HttpStatus.BAD_REQUEST); 
         } else if (e instanceof InfrastructureInventoryUpdateRepositoryImplException || e instanceof InfrastructureProductRepositoryImplException) { 
             return new ResponseEntity<>(new ErrorResponse("Internal server error", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR); 
         } else { 
             return new ResponseEntity<>(new ErrorResponse("Unexpected error", "An unexpected error occurred. Please try again later."), HttpStatus.INTERNAL_SERVER_ERROR); 
         } 
     } 
  
     private static class ErrorResponse { 
         private String error; 
         private String message; 
  
         public ErrorResponse(String error, String message) { 
             this.error = error; 
             this.message = message; 
         } 
  
         public String getError() { 
             return error; 
         } 
  
         public String getMessage() { 
             return message; 
         } 
     } 
 } 
 