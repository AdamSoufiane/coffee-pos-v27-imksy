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
  
     @Bean 
     public DataSource dataSource() { 
         return DataSourceBuilder.create() 
                 .url(databaseUrl) 
                 .username(databaseUsername) 
                 .password(databasePassword) 
                 .driverClassName(databaseDriverClassName) 
                 .build(); 
     } 
  
     @Bean 
     public LocalContainerEntityManagerFactoryBean entityManagerFactory() { 
         LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean(); 
         em.setDataSource(dataSource()); 
         em.setPackagesToScan("ai.shreds.domain"); 
         em.setJpaVendorAdapter(new HibernateJpaVendorAdapter()); 
         return em; 
     } 
  
     @Bean 
     public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) { 
         JpaTransactionManager transactionManager = new JpaTransactionManager(); 
         transactionManager.setEntityManagerFactory(entityManagerFactory); 
         return transactionManager; 
     } 
 }