package ai.shreds.application; 
  
 import ai.shreds.shared.AdapterProductRequestDTO; 
 import ai.shreds.shared.AdapterProductResponseDTO; 
  
 /** 
  * Interface for updating product details. 
  */ 
 public interface ApplicationUpdateProductInputPort { 
     /** 
      * Updates the details of an existing product. 
      * 
      * @param request the product update request data transfer object 
      * @return the response data transfer object containing the status and product ID 
      */ 
     AdapterProductResponseDTO updateProduct(AdapterProductRequestDTO request); 
 } 
 