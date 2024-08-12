package ai.shreds.infrastructure; 
  
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
 import org.springframework.transaction.PlatformTransactionManager; 
 import org.springframework.transaction.TransactionDefinition; 
 import org.springframework.transaction.TransactionStatus; 
 import org.springframework.transaction.support.DefaultTransactionDefinition; 
 import lombok.extern.slf4j.Slf4j; 
  
 @Slf4j 
 @Service 
 public class InfrastructureTransactionService { 
  
     private final PlatformTransactionManager transactionManager; 
  
     @Autowired 
     public InfrastructureTransactionService(PlatformTransactionManager transactionManager) { 
         this.transactionManager = transactionManager; 
     } 
  
     /** 
      * Starts a new transaction. 
      *  
      * @return TransactionStatus object representing the current transaction state. 
      */ 
     public TransactionStatus startTransaction() { 
         if (transactionManager == null) { 
             throw new IllegalStateException("Transaction Manager is not initialized."); 
         } 
         DefaultTransactionDefinition def = new DefaultTransactionDefinition(); 
         def.setName("ProductCreationTransaction"); 
         def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED); 
         TransactionStatus status = transactionManager.getTransaction(def); 
         log.info("Transaction started: {}", status); 
         return status; 
     } 
  
     /** 
      * Commits the current transaction. 
      *  
      * @param status TransactionStatus object representing the current transaction state. 
      */ 
     public void commitTransaction(TransactionStatus status) { 
         if (status != null && !status.isCompleted()) { 
             transactionManager.commit(status); 
             log.info("Transaction committed: {}", status); 
         } 
     } 
  
     /** 
      * Rolls back the current transaction. 
      *  
      * @param status TransactionStatus object representing the current transaction state. 
      */ 
     public void rollbackTransaction(TransactionStatus status) { 
         if (status != null && !status.isCompleted()) { 
             transactionManager.rollback(status); 
             log.info("Transaction rolled back: {}", status); 
         } 
     } 
 } 
 