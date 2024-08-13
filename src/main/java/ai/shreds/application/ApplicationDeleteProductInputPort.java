package ai.shreds.application; 
  
 import java.util.UUID; 
  
 /** 
  * ApplicationDeleteProductInputPort defines the contract for deleting a product by its unique identifier. 
  * This interface is implemented by the ApplicationProductService class. 
  *  
  * Note: Use Lombok annotations in the implementing classes to reduce boilerplate code. 
  */ 
 public interface ApplicationDeleteProductInputPort { 
     void deleteProduct(UUID id); 
 } 
 