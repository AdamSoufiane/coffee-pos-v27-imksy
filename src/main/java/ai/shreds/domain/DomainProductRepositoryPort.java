package ai.shreds.domain; 
  
 import java.util.UUID; 
  
 /** 
  * DomainProductRepositoryPort is an interface that provides an abstraction for database operations related to the Product entity. 
  * This ensures that the application layer can interact with the database without being tightly coupled to any specific database implementation. 
  */ 
 public interface DomainProductRepositoryPort { 
     /** 
      * Saves a given product entity to the database. 
      *  
      * @param product the product entity to be saved 
      */ 
     void save(DomainProductEntity product); 
 } 
 