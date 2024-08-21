package ai.shreds.infrastructure; 
  
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 public class InfrastructureProductRepositoryImplException extends Exception { 
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureProductRepositoryImplException.class); 
  
     public InfrastructureProductRepositoryImplException(String message) { 
         super(message); 
         logError(message); 
     } 
  
     private void logError(String message) { 
         logger.error(message); 
     } 
 } 
  
 // Implementation Note: Consider using Lombok annotations for constructors and logging.