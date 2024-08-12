package ai.shreds.domain; 
  
 import java.util.UUID; 
  
 /** 
  * Interface for updating product details within the domain layer. 
  */ 
 public interface DomainProductUpdatePort { 
     /** 
      * Updates the details of an existing product. 
      * 
      * @param product the product entity with updated details 
      * @return the updated product entity 
      */ 
     DomainProductEntity updateProduct(DomainProductEntity product); 
 } 
 