package ai.shreds.infrastructure; 
  
 import org.springframework.beans.factory.annotation.Value; 
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import org.springframework.data.jpa.repository.config.EnableJpaRepositories; 
 import org.springframework.orm.jpa.JpaTransactionManager; 
 import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean; 
 import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter; 
 import org.springframework.transaction.PlatformTransactionManager; 
  
 import javax.sql.DataSource; 
 import org.apache.commons.dbcp2.BasicDataSource; 
 import java.util.Properties; 
  
 @Configuration 
 @EnableJpaRepositories(basePackages = "ai.shreds.infrastructure") 
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
  
     @Value("${spring.jpa.show-sql}") 
     private boolean showSql; 
  
     @Value("${spring.jpa.hibernate.ddl-auto}") 
     private String hbm2ddlAuto; 
  
     @Bean 
     public DataSource dataSource() { 
         BasicDataSource dataSource = new BasicDataSource(); 
         dataSource.setUrl(databaseUrl); 
         dataSource.setUsername(databaseUsername); 
         dataSource.setPassword(databasePassword); 
         dataSource.setDriverClassName(databaseDriverClassName); 
         return dataSource; 
     } 
  
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
  
     @Bean 
     public PlatformTransactionManager transactionManager() { 
         JpaTransactionManager transactionManager = new JpaTransactionManager(); 
         transactionManager.setEntityManagerFactory(entityManagerFactory().getObject()); 
         return transactionManager; 
     } 
  
     private Properties hibernateProperties() { 
         Properties properties = new Properties(); 
         properties.setProperty("hibernate.dialect", hibernateDialect); 
         properties.setProperty("hibernate.show_sql", String.valueOf(showSql)); 
         properties.setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto); 
         return properties; 
     } 
 }