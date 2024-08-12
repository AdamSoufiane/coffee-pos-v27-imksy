package ai.shreds.infrastructure; 
  
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
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
  
 /** 
  * Configuration class for setting up the database connection, EntityManagerFactory, and TransactionManager. 
  */ 
 @Configuration 
 @EnableTransactionManagement 
 @EnableJpaRepositories(basePackages = "ai.shreds.domain") 
 public class InfrastructureDatabaseConfig { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureDatabaseConfig.class); 
  
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
  
     @Value("${spring.jpa.hibernate.ddl-auto}") 
     private String ddlAuto; 
  
     /** 
      * Creates and configures the DataSource bean. 
      *  
      * @return the configured DataSource 
      */ 
     @Bean 
     public DataSource dataSource() { 
         validateConfiguration(); 
         HikariConfig hikariConfig = new HikariConfig(); 
         hikariConfig.setJdbcUrl(dbUrl); 
         hikariConfig.setUsername(dbUsername); 
         hikariConfig.setPassword(dbPassword); 
         hikariConfig.setDriverClassName(dbDriverClassName); 
         logger.info("DataSource configured with URL: {}", dbUrl); 
         return new HikariDataSource(hikariConfig); 
     } 
  
     /** 
      * Creates and configures the EntityManagerFactory bean. 
      *  
      * @return the configured LocalContainerEntityManagerFactoryBean 
      */ 
     @Bean 
     public LocalContainerEntityManagerFactoryBean entityManagerFactory() { 
         LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean(); 
         em.setDataSource(dataSource()); 
         em.setPackagesToScan("ai.shreds.domain"); 
  
         HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter(); 
         em.setJpaVendorAdapter(vendorAdapter); 
         em.setJpaProperties(hibernateProperties()); 
  
         if (em.getObject() == null) { 
             throw new IllegalStateException("EntityManagerFactory initialization failed"); 
         } 
  
         logger.info("EntityManagerFactory configured"); 
         return em; 
     } 
  
     /** 
      * Creates and configures the TransactionManager bean. 
      *  
      * @return the configured PlatformTransactionManager 
      */ 
     @Bean 
     public PlatformTransactionManager transactionManager() { 
         JpaTransactionManager transactionManager = new JpaTransactionManager(); 
         transactionManager.setEntityManagerFactory(entityManagerFactory().getObject()); 
         logger.info("TransactionManager configured"); 
         return transactionManager; 
     } 
  
     /** 
      * Configures Hibernate properties. 
      *  
      * @return the configured Properties 
      */ 
     private Properties hibernateProperties() { 
         Properties properties = new Properties(); 
         properties.setProperty("hibernate.dialect", hibernateDialect); 
         properties.setProperty("hibernate.show_sql", String.valueOf(showSql)); 
         properties.setProperty("hibernate.hbm2ddl.auto", ddlAuto); 
         return properties; 
     } 
  
     /** 
      * Validates the configuration properties to ensure they are not null or empty. 
      */ 
     private void validateConfiguration() { 
         if (dbUrl == null || dbUrl.isEmpty()) { 
             throw new IllegalArgumentException("Database URL must not be null or empty"); 
         } 
         if (dbUsername == null || dbUsername.isEmpty()) { 
             throw new IllegalArgumentException("Database username must not be null or empty"); 
         } 
         if (dbPassword == null || dbPassword.isEmpty()) { 
             throw new IllegalArgumentException("Database password must not be null or empty"); 
         } 
         if (dbDriverClassName == null || dbDriverClassName isEmpty()) { 
             throw new IllegalArgumentException("Database driver class name must not be null or empty"); 
         } 
     } 
 }