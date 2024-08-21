package ai.shreds.infrastructure; 
  
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import org.springframework.orm.jpa.JpaTransactionManager; 
 import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean; 
 import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter; 
 import org.springframework.transaction.PlatformTransactionManager; 
 import org.springframework.transaction.annotation.EnableTransactionManagement; 
  
 import javax.persistence.EntityManagerFactory; 
 import javax.sql.DataSource; 
 import org.springframework.boot.jdbc.DataSourceBuilder; 
 import org.springframework.beans.factory.annotation.Value; 
 import java.util.Properties; 
  
 @Configuration 
 @EnableTransactionManagement 
 public class InfrastructureDatabaseConfig { 
  
     @Value("${spring.datasource.url}") 
     private String databaseUrl; 
  
     @Value("${spring.datasource.username}") 
     private String databaseUsername; 
  
     @Value("${spring.datasource.password}") 
     private String databasePassword; 
  
     @Value("${spring.datasource.driver-class-name}") 
     private String databaseDriverClassName; 
  
     @Value("${spring.jpa.properties.hibernate.dialect}") 
     private String hibernateDialect; 
  
     // Method to configure the DataSource 
     @Bean 
     public DataSource dataSource() { 
         if (databaseUrl == null || databaseUrl.isEmpty() || 
             databaseUsername == null || databaseUsername.isEmpty() || 
             databasePassword == null || databasePassword.isEmpty() || 
             databaseDriverClassName == null || databaseDriverClassName.isEmpty()) { 
             throw new IllegalArgumentException("Database connection properties cannot be null or empty"); 
         } 
         try { 
             return DataSourceBuilder.create() 
                     .url(databaseUrl) 
                     .username(databaseUsername) 
                     .password(databasePassword) 
                     .driverClassName(databaseDriverClassName) 
                     .build(); 
         } catch (Exception e) { 
             throw new RuntimeException("Failed to create DataSource", e); 
         } 
     } 
  
     // Method to configure the EntityManagerFactory 
     @Bean 
     public LocalContainerEntityManagerFactoryBean entityManagerFactory() { 
         try { 
             LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean(); 
             entityManagerFactory.setDataSource(dataSource()); 
             entityManagerFactory.setPackagesToScan("ai.shreds.domain"); 
             entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter()); 
  
             Properties jpaProperties = new Properties(); 
             jpaProperties.put("hibernate.dialect", hibernateDialect); 
             entityManagerFactory.setJpaProperties(jpaProperties); 
  
             return entityManagerFactory; 
         } catch (Exception e) { 
             throw new RuntimeException("Failed to create EntityManagerFactory", e); 
         } 
     } 
  
     // Method to configure the PlatformTransactionManager 
     @Bean 
     public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) { 
         try { 
             return new JpaTransactionManager(entityManagerFactory); 
         } catch (Exception e) { 
             throw new RuntimeException("Failed to create TransactionManager", e); 
         } 
     } 
 } 
  
 // Implementation Note: Use Lombok annotations for getter and setter methods.