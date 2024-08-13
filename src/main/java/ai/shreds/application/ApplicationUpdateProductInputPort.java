package ai.shreds.application; 
  
 import ai.shreds.adapter.AdapterUpdateProductRequest; 
 import ai.shreds.adapter.AdapterProductResponse; 
 import java.util.UUID; 
  
 /** 
  * Interface for updating a product in the application layer. 
  */ 
 public interface ApplicationUpdateProductInputPort { 
     /** 
      * Updates a product with the given ID and request details. 
      * 
      * @param id      the unique identifier of the product 
      * @param request the request containing updated product details 
      * @return the response containing the updated product details 
      */ 
     AdapterProductResponse updateProduct(UUID id, AdapterUpdateProductRequest request); 
 } 
  
 // Implementation Note: Use Lombok annotations if necessary. 
 // Implementation Note: Check if Lombok annotations are needed for any additional fields or methods.