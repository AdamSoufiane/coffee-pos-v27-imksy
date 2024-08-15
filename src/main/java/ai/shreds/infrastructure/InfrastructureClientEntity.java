package ai.shreds.infrastructure; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Builder; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import javax.persistence.Column; 
 import javax.persistence.Entity; 
 import javax.persistence.Id; 
 import javax.persistence.Table; 
 import java.time.LocalDateTime; 
 import java.util.UUID; 
  
 @Data 
 @Builder 
 @NoArgsConstructor 
 @AllArgsConstructor 
 @Entity 
 @Table(name = "Client") 
 public class InfrastructureClientEntity { 
  
     @Id 
     @Column(name = "clientId", nullable = false, unique = true) 
     private UUID clientId; 
  
     @Column(name = "firstName", nullable = false) 
     private String firstName; 
  
     @Column(name = "lastName", nullable = false) 
     private String lastName; 
  
     @Column(name = "email", nullable = false) 
     private String email; 
  
     @Column(name = "phoneNumber", nullable = false) 
     private String phoneNumber; 
  
     @Column(name = "address", nullable = false) 
     private String address; 
  
     @Column(name = "zip_code", nullable = false) 
     private String zipCode; 
  
     @Column(name = "city", nullable = false) 
     private String city; 
  
     @Column(name = "registrationDate", nullable = false) 
     private LocalDateTime registrationDate; 
 } 
 