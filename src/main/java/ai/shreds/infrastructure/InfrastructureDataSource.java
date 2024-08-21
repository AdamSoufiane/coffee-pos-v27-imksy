package ai.shreds.infrastructure; 
  
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import org.springframework.beans.factory.annotation.Value; 
 import javax.sql.DataSource; 
 import org.springframework.boot.jdbc.DataSourceBuilder; 
  
 @Configuration 
 public class InfrastructureDataSource { 
  
     @Value("${spring.datasource.url}") 
     private String url; 
  
     @Value("${spring.datasource.username}") 
     private String username; 
  
     @Value("${spring.datasource.password}") 
     private String password; 
  
     @Value("${spring.datasource.driver-class-name}") 
     private String driverClassName; 
  
     @Bean 
     public DataSource dataSource() { 
         return DataSourceBuilder.create() 
                 .url(url) 
                 .username(username) 
                 .password(password) 
                 .driverClassName(driverClassName) 
                 .build(); 
     } 
 } 
 // Note: Use Lombok annotations for getters and setters if needed.