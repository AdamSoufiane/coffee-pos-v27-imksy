package ai.shreds.adapter; 
  
 import lombok.Data; 
 import javax.validation.constraints.Email; 
 import javax.validation.constraints.Future; 
 import javax.validation.constraints.NotBlank; 
 import javax.validation.constraints.NotNull; 
 import javax.validation.constraints.Pattern; 
 import javax.validation.constraints.Size; 
 import java.util.Date; 
  
 @Data 
 public class AdapterCreateSupplierRequest { 
     @NotBlank(message = "Name is mandatory") 
     @Size(max = 255, message = "Name must not exceed 255 characters") 
     private String name; 
  
     @NotBlank(message = "Phone number is mandatory") 
     @Pattern(regexp = "^\\d{3}-\\d{3}-\\d{4}$", message = "Phone number must be in the format XXX-XXX-XXXX") 
     private String contact_info_phone; 
  
     @NotBlank(message = "Email is mandatory") 
     @Email(message = "Email should be valid") 
     private String contact_info_email; 
  
     @NotBlank(message = "Address is mandatory") 
     @Size(max = 255, message = "Address must not exceed 255 characters") 
     private String address; 
  
     @NotBlank(message = "Zip code is mandatory") 
     @Size(max = 10, message = "Zip code must not exceed 10 characters") 
     private String zip_code; 
  
     @NotBlank(message = "City is mandatory") 
     @Size(max = 100, message = "City must not exceed 100 characters") 
     private String city; 
  
     @NotBlank(message = "Registration code is mandatory") 
     @Size(max = 50, message = "Registration code must not exceed 50 characters") 
     private String rc; 
  
     @NotNull(message = "Echeance date is mandatory") 
     @Future(message = "Echeance date must be a future date") 
     private Date echeance_date; 
 } 
 