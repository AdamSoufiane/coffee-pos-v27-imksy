package ai.shreds.infrastructure; 
  
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean; 
 import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter; 
 import javax.sql.DataSource; 
 import java.util.HashMap; 
 import java.util.Map; 
  
 @Configuration 
 public class InfrastructureLocalContainerEntityManagerFactoryBean { 
  
     private final DataSource dataSource; 
  
     @Autowired 
     public InfrastructureLocalContainerEntityManagerFactoryBean(DataSource dataSource) { 
         this.dataSource = dataSource; 
     } 
  
     @Bean 
     public LocalContainerEntityManagerFactoryBean entityManagerFactory() { 
         LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean(); 
         em.setDataSource(dataSource); 
         em.setPackagesToScan("ai.shreds.domain"); 
  
         HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter(); 
         em.setJpaVendorAdapter(vendorAdapter); 
  
         Map<String, Object> properties = new HashMap<>(); 
         properties.put("hibernate.hbm2ddl.auto", "update"); 
         properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect"); 
         properties.put("hibernate.show_sql", true); 
         em.setJpaPropertyMap(properties); 
  
         return em; 
     } 
 } 
  
 // Implementation Note: Use Lombok annotations (@Getter, @Setter) for getter and setter methods if applicable. 
 // Implementation Note: Ensure proper transaction management using @Transactional annotation if required. 
 // Implementation Note: Clarify the use of @Configuration annotation for Spring configuration class. 
 // Implementation Note: Ensure that the dataSource bean is properly configured and available.