package ai.shreds.domain; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Builder; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import javax.persistence.*; 
 import javax.validation.constraints.Email; 
 import javax.validation.constraints.Future; 
 import javax.validation.constraints.NotBlank; 
 import javax.validation.constraints.Pattern; 
 import java.sql.Timestamp; 
 import java.util.Date; 
  
 @Entity 
 @Table(name = "Supplier", uniqueConstraints = {@UniqueConstraint(columnNames = "name")}) 
 @Data 
 @Builder 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class DomainSupplierEntity { 
     @Id 
     @GeneratedValue(strategy = GenerationType.IDENTITY) 
     private Long id; 
  
     @NotBlank 
     @Column(nullable = false) 
     private String name; 
  
     @Embedded 
     private DomainContactInfoValue contactInfo; 
  
     @Embedded 
     private DomainAddressValue address; 
  
     @NotBlank 
     @Column(nullable = false, unique = true) 
     private String rc; 
  
     @Future 
     @Column(nullable = false) 
     private Date echeanceDate; 
  
     @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP") 
     private Timestamp createdAt; 
  
     @Column(nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP") 
     private Timestamp updatedAt; 
 } 
  
 @Embeddable 
 @Data 
 @Builder 
 @NoArgsConstructor 
 @AllArgsConstructor 
 class DomainContactInfoValue { 
     @NotBlank 
     @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Invalid phone number") 
     @Column(name = "contact_info_phone", nullable = false) 
     private String phone; 
  
     @NotBlank 
     @Email 
     @Column(name = "contact_info_email", nullable = false) 
     private String email; 
 } 
  
 @Embeddable 
 @Data 
 @Builder 
 @NoArgsConstructor 
 @AllArgsConstructor 
 class DomainAddressValue { 
     @NotBlank 
     @Column(name = "address", nullable = false) 
     private String address; 
  
     @NotBlank 
     @Column(name = "zip_code", nullable = false) 
     private String zipCode; 
  
     @NotBlank 
     @Column(name = "city", nullable = false) 
     private String city; 
 }