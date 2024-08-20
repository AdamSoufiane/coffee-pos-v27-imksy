package ai.shreds.infrastructure; 
  
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import org.springframework.orm.jpa.JpaTransactionManager; 
 import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean; 
 import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter; 
 import org.springframework.transaction.PlatformTransactionManager; 
 import javax.sql.DataSource; 
 import org.springframework.boot.jdbc.DataSourceBuilder; 
  
 @Configuration 
 public class InfrastructureDatabaseConfig { 
  
     /** 
      * Creates and configures the DataSource bean. 
      * @return the configured DataSource 
      */ 
     @Bean 
     public DataSource createDataSource() { 
         try { 
             return DataSourceBuilder.create() 
                     .driverClassName("com.mysql.cj.jdbc.Driver") 
                     .url("jdbc:mysql://localhost:3306/transactiondb") 
                     .username("root") 
                     .password("password") 
                     .build(); 
         } catch (Exception e) { 
             throw new InfrastructureDatabaseException("Error creating DataSource", e); 
         } 
     } 
  
     /** 
      * Configures and returns the LocalContainerEntityManagerFactoryBean. 
      * @return the configured LocalContainerEntityManagerFactoryBean 
      */ 
     @Bean 
     public LocalContainerEntityManagerFactoryBean configureEntityManagerFactory() { 
         try { 
             LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean(); 
             em.setDataSource(createDataSource()); 
             em.setPackagesToScan("ai.shreds.domain"); 
             HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter(); 
             em.setJpaVendorAdapter(vendorAdapter); 
             return em; 
         } catch (Exception e) { 
             throw new InfrastructureDatabaseException("Error configuring EntityManagerFactory", e); 
         } 
     } 
  
     /** 
      * Sets up and returns the PlatformTransactionManager. 
      * @return the configured PlatformTransactionManager 
      */ 
     @Bean 
     public PlatformTransactionManager setupTransactionManager() { 
         try { 
             JpaTransactionManager transactionManager = new JpaTransactionManager(); 
             transactionManager.setEntityManagerFactory(configureEntityManagerFactory().getObject()); 
             return transactionManager; 
         } catch (Exception e) { 
             throw new InfrastructureDatabaseException("Error setting up TransactionManager", e); 
         } 
     } 
 } 
 