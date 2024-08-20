package ai.shreds.infrastructure; 
  
 import org.springframework.beans.factory.annotation.Value; 
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import org.springframework.jdbc.datasource.DriverManagerDataSource; 
 import javax.sql.DataSource; 
 import lombok.Getter; 
 import lombok.Setter; 
 import lombok.NoArgsConstructor; 
  
 @Configuration 
 @NoArgsConstructor 
 @Getter 
 @Setter 
 public class InfrastructureDataSource { 
  
     @Value("${spring.datasource.url}") 
     private String url; 
  
     @Value("${spring.datasource.username}") 
     private String username; 
  
     @Value("${spring.datasource.password}") 
     private String password; 
  
     @Value("${spring.datasource.driver-class-name}") 
     private String driverClassName; 
  
     /** 
      * Configures and provides a DataSource bean for database interactions. 
      * 
      * @return configured DataSource 
      */ 
     @Bean 
     public DataSource dataSource() { 
         try { 
             DriverManagerDataSource dataSource = new DriverManagerDataSource(); 
             dataSource.setDriverClassName(driverClassName); 
             dataSource.setUrl(url); 
             dataSource.setUsername(username); 
             dataSource.setPassword(password); 
             return dataSource; 
         } catch (Exception e) { 
             throw new InfrastructureDatabaseException("Failed to configure DataSource", e); 
         } 
     } 
 } 
 