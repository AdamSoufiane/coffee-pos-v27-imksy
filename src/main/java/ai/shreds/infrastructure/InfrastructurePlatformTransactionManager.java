package ai.shreds.infrastructure; 
  
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import org.springframework.orm.jpa.JpaTransactionManager; 
 import org.springframework.transaction.PlatformTransactionManager; 
 import javax.persistence.EntityManagerFactory; 
  
 /** 
  * InfrastructurePlatformTransactionManager is responsible for managing transactions within the infrastructure layer. 
  * It provides a method to configure and return a PlatformTransactionManager bean, which is used by Spring to manage transactions. 
  */ 
 @Configuration 
 public class InfrastructurePlatformTransactionManager { 
  
     private final EntityManagerFactory entityManagerFactory; 
  
     public InfrastructurePlatformTransactionManager(EntityManagerFactory entityManagerFactory) { 
         this.entityManagerFactory = entityManagerFactory; 
     } 
  
     @Bean 
     public PlatformTransactionManager transactionManager() { 
         JpaTransactionManager transactionManager = new JpaTransactionManager(); 
         transactionManager.setEntityManagerFactory(entityManagerFactory); 
         return transactionManager; 
     } 
 } 
  
 // Implementation Note: Consider using Lombok annotations (e.g., @RequiredArgsConstructor) to reduce boilerplate code for the constructor.