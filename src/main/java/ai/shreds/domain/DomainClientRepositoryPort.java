package ai.shreds.domain; 
  
 import java.util.UUID; 
  
 /** 
  * Interface for Client Repository operations. 
  */ 
 public interface DomainClientRepositoryPort { 
     /** 
      * Save a client entity to the database. 
      * @param client the client entity to save. 
      */ 
     void save(DomainClientEntity client); 
  
     /** 
      * Find a client by their unique identifier. 
      * @param id the unique identifier of the client. 
      * @return the client entity. 
      */ 
     DomainClientEntity findById(UUID id); 
 }