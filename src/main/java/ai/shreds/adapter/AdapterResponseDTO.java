package ai.shreds.adapter; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Builder; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 import javax.validation.constraints.NotBlank; 
 import javax.validation.constraints.NotNull; 
 import java.sql.Timestamp; 
 import java.util.Date; 
  
 @Data 
 @Builder 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class AdapterResponseDTO { 
     private Long id; 
     @NotBlank 
     private String name; 
     @NotBlank 
     private String contact_info_phone; 
     @NotBlank 
     private String contact_info_email; 
     @NotBlank 
     private String address; 
     @NotBlank 
     private String zip_code; 
     @NotBlank 
     private String city; 
     @NotBlank 
     private String rc; 
     @NotNull 
     private Date echeance_date; 
     private Timestamp created_at; 
     private Timestamp updated_at; 
 }