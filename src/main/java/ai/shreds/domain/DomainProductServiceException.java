package ai.shreds.domain; 
  
 import ai.shreds.shared.AdapterProductResponseDTO; 
 import lombok.extern.slf4j.Slf4j; 
 import org.springframework.stereotype.Component; 
  
 /** 
  * This class handles exceptions that occur within the domain logic related to product updates. 
  */ 
 @Slf4j 
 @Component 
 public class DomainProductServiceException { 
  
     /** 
      * Handles exceptions by logging the error and converting it into a standardized response format. 
      *  
      * @param exception the exception that occurred 
      * @return a standardized response containing the error message 
      */ 
     public AdapterProductResponseDTO handleException(Exception exception) { 
         log.error("Exception occurred in domain service: {}", exception.getMessage(), exception); 
         AdapterProductResponseDTO response = new AdapterProductResponseDTO(); 
         response.setStatus("failure"); 
         response.setProduct_id(null); // Assuming we don't have a product ID in case of an exception 
         response.setMessage(exception.getMessage()); 
         return response; 
     } 
 } 
 