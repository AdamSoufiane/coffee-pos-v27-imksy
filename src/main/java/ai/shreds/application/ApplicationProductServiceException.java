package ai.shreds.application; 
  
 import ai.shreds.shared.AdapterProductResponseDTO; 
 import org.springframework.http.HttpStatus; 
 import org.springframework.web.bind.annotation.ExceptionHandler; 
 import org.springframework.web.bind.annotation.ResponseStatus; 
 import org.springframework.web.bind.annotation.RestControllerAdvice; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 @RestControllerAdvice 
 public class ApplicationProductServiceException { 
  
     private static final Logger logger = LoggerFactory.getLogger(ApplicationProductServiceException.class); 
  
     @ExceptionHandler(IllegalArgumentException.class) 
     @ResponseStatus(HttpStatus.BAD_REQUEST) 
     public AdapterProductResponseDTO handleIllegalArgumentException(IllegalArgumentException exception) { 
         logger.error("Invalid product data: ", exception); 
         return AdapterProductResponseDTO.builder() 
                 .status("failure") 
                 .message("Invalid product data: " + exception.getMessage()) 
                 .build(); 
     } 
  
     @ExceptionHandler(Exception.class) 
     @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) 
     public AdapterProductResponseDTO handleException(Exception exception) { 
         logger.error("Internal server error: ", exception); 
         return AdapterProductResponseDTO.builder() 
                 .status("failure") 
                 .message("Internal server error: " + exception.getMessage()) 
                 .build(); 
     } 
 } 
 