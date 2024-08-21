package ai.shreds.application; 
  
 import ai.shreds.shared.AdapterEnrollClientRequest; 
 import ai.shreds.shared.AdapterEnrollClientResponse; 
  
 /** 
  * ApplicationEnrollClientInputPort defines the contract for enrolling a client into a loyalty program. 
  */ 
 public interface ApplicationEnrollClientInputPort { 
     /** 
      * Enrolls a client into a loyalty program. 
      * 
      * @param request the request containing client and loyalty program details 
      * @return the response indicating the result of the enrollment 
      */ 
     AdapterEnrollClientResponse enrollClient(AdapterEnrollClientRequest request); 
 } 
 