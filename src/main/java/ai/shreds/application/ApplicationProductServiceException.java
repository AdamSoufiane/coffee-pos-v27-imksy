package ai.shreds.application; 
  
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.web.bind.annotation.RestControllerAdvice; 
  
 @RestControllerAdvice 
 public class ApplicationProductServiceException { 
  
     private static final Logger logger = LoggerFactory.getLogger(ApplicationProductServiceException.class); 
  
     @ExceptionHandler(Exception.class) 
     public void handleException(Exception e) { 
         // Log the exception details with more context 
         logger.error("An error occurred in ApplicationProductService: {}", e.getMessage(), e); 
         // Here, you could add additional logic to transform the exception into a custom exception 
         // or handle it in a way that is consistent with your application's error handling strategy. 
     } 
 } 
 