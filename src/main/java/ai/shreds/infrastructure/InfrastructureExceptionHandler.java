package ai.shreds.infrastructure; 
  
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.stereotype.Component; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.web.bind.annotation.ResponseStatus; 
 import org.springframework.http.HttpStatus; 
  
 @Component 
 public class InfrastructureExceptionHandler { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureExceptionHandler.class); 
  
     @ExceptionHandler(Throwable.class) 
     @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) 
     public void handleException(Throwable e) { 
         // Convert the Throwable to a custom InfrastructureException 
         InfrastructureException infrastructureException = new InfrastructureException("An error occurred in the infrastructure layer", e); 
         // Log the exception details with more context information 
         logger.error("An error occurred in the infrastructure layer: ", infrastructureException); 
         // Here you can add more logic if needed. 
     } 
 } 
  
 class InfrastructureException extends RuntimeException { 
     public InfrastructureException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 } 
 