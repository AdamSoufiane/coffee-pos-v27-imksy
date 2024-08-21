package ai.shreds.infrastructure; 
  
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import org.springframework.transaction.PlatformTransactionManager; 
 import org.springframework.transaction.annotation.EnableTransactionManagement; 
 import org.springframework.orm.jpa.JpaTransactionManager; 
 import javax.persistence.EntityManagerFactory; 
 import org.springframework.beans.factory.annotation.Autowired; 
  
 @Configuration 
 @EnableTransactionManagement 
 public class InfrastructurePlatformTransactionManager { 
  
     private final EntityManagerFactory entityManagerFactory; 
  
     @Autowired 
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
 // Implementation Note: Consider using Lombok annotations for getter and setter methods if applicable.