package ai.shreds.infrastructure; 
  
 import org.springframework.beans.factory.annotation.Value; 
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import org.springframework.data.jpa.repository.config.EnableJpaRepositories; 
 import org.springframework.orm.jpa.JpaTransactionManager; 
 import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean; 
 import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter; 
 import org.springframework.transaction.PlatformTransactionManager; 
 import org.springframework.transaction.annotation.EnableTransactionManagement; 
  
 import javax.sql.DataSource; 
 import com.zaxxer.hikari.HikariConfig; 
 import com.zaxxer.hikari.HikariDataSource; 
 import java.util.Properties; 
  
 @Configuration 
 @EnableJpaRepositories(basePackages = "ai.shreds.infrastructure") 
 @EnableTransactionManagement 
 public class InfrastructureJpaConfig { 
  
     @Value("${spring.datasource.url}") 
     private String dbUrl; 
  
     @Value("${spring.datasource.username}") 
     private String dbUsername; 
  
     @Value("${spring.datasource.password}") 
     private String dbPassword; 
  
     @Value("${spring.datasource.driver-class-name}") 
     private String dbDriverClassName; 
  
     @Value("${spring.jpa.properties.hibernate.dialect}") 
     private String hibernateDialect; 
  
     @Value("${spring.jpa.show-sql}") 
     private boolean showSql; 
  
     /** 
      * Configures and returns the DataSource bean. 
      * @return DataSource 
      */ 
     @Bean 
     public DataSource dataSource() { 
         if (dbUrl == null || dbUsername == null || dbPassword == null || dbDriverClassName == null) { 
             throw new IllegalArgumentException("Database properties must not be null"); 
         } 
         HikariConfig hikariConfig = new HikariConfig(); 
         hikariConfig.setJdbcUrl(dbUrl); 
         hikariConfig.setUsername(dbUsername); 
         hikariConfig.setPassword(dbPassword); 
         hikariConfig.setDriverClassName(dbDriverClassName); 
         return new HikariDataSource(hikariConfig); 
     } 
  
     /** 
      * Configures and returns the EntityManagerFactory bean. 
      * @return LocalContainerEntityManagerFactoryBean 
      */ 
     @Bean 
     public LocalContainerEntityManagerFactoryBean entityManagerFactory() { 
         LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean(); 
         em.setDataSource(dataSource()); 
         em.setPackagesToScan("ai.shreds.domain"); 
  
         HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter(); 
         em.setJpaVendorAdapter(vendorAdapter); 
         em.setJpaProperties(hibernateProperties()); 
  
         return em; 
     } 
  
     /** 
      * Configures and returns the TransactionManager bean. 
      * @return PlatformTransactionManager 
      */ 
     @Bean 
     public PlatformTransactionManager transactionManager() { 
         JpaTransactionManager transactionManager = new JpaTransactionManager(); 
         transactionManager.setEntityManagerFactory(entityManagerFactory().getObject()); 
         return transactionManager; 
     } 
  
     /** 
      * Sets Hibernate properties. 
      * @return Properties 
      */ 
     private Properties hibernateProperties() { 
         Properties properties = new Properties(); 
         properties.put("hibernate.dialect", hibernateDialect); 
         properties.put("hibernate.show_sql", showSql); 
         return properties; 
     } 
 } 
 