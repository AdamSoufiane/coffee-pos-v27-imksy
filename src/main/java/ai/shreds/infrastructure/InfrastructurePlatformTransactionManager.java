package ai.shreds.infrastructure; 
  
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import org.springframework.orm.jpa.JpaTransactionManager; 
 import org.springframework.transaction.PlatformTransactionManager; 
 import javax.persistence.EntityManagerFactory; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 /** 
  * Configuration class for setting up the PlatformTransactionManager. 
  */ 
 @Configuration 
 public class InfrastructurePlatformTransactionManager { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructurePlatformTransactionManager.class); 
     private final EntityManagerFactory entityManagerFactory; 
  
     /** 
      * Constructor for InfrastructurePlatformTransactionManager. 
      * 
      * @param entityManagerFactory the EntityManagerFactory to be used by the transaction manager 
      */ 
     public InfrastructurePlatformTransactionManager(EntityManagerFactory entityManagerFactory) { 
         this.entityManagerFactory = entityManagerFactory; 
     } 
  
     /** 
      * Defines the PlatformTransactionManager bean. 
      * 
      * @return a configured JpaTransactionManager 
      */ 
     @Bean 
     public PlatformTransactionManager transactionManager() { 
         logger.info("Configuring JpaTransactionManager"); 
         JpaTransactionManager transactionManager = new JpaTransactionManager(); 
         transactionManager.setEntityManagerFactory(entityManagerFactory); 
         return transactionManager; 
     } 
 }