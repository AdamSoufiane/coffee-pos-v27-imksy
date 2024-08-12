package ai.shreds.application; 
  
 import ai.shreds.shared.SharedProductDTO; 
 import ai.shreds.shared.SharedRequestParams; 
  
 /** 
  * Interface for creating a product in the application layer. 
  */ 
 public interface ApplicationCreateProductInputPort { 
     /** 
      * Creates a new product based on the provided request parameters. 
      * 
      * @param request the parameters for creating a new product 
      * @return the created product details 
      */ 
     SharedProductDTO createProduct(SharedRequestParams request); 
 } 
 