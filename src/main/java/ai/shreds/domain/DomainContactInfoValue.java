package ai.shreds.domain; 
  
 import lombok.Getter; 
 import lombok.AllArgsConstructor; 
 import java.util.Objects; 
 import java.util.regex.Pattern; 
  
 /** 
  * DomainContactInfoValue is a value object representing the contact information of a client. 
  * It ensures that the phone number and email address are valid according to the specified business rules. 
  */ 
 @Getter 
 @AllArgsConstructor 
 public class DomainContactInfoValue { 
  
     private final String phone; 
     private final String email; 
  
     /** 
      * Constructs a new DomainContactInfoValue with the specified phone number and email address. 
      * 
      * @param phone the phone number of the client 
      * @param email the email address of the client 
      * @throws IllegalArgumentException if the phone or email is invalid 
      */ 
     public DomainContactInfoValue(String phone, String email) { 
         if (!isValidPhone(phone)) { 
             throw new IllegalArgumentException("Invalid phone number format"); 
         } 
         if (!isValidEmail(email)) { 
             throw new IllegalArgumentException("Invalid email address format"); 
         } 
         this.phone = phone; 
         this.email = email; 
     } 
  
     private boolean isValidPhone(String phone) { 
         // Example phone validation logic, adjust regex as needed 
         String phoneRegex = "^\\+?[0-9. ()-]{7,25}$"; 
         return Pattern.matches(phoneRegex, phone); 
     } 
  
     private boolean isValidEmail(String email) { 
         // Example email validation logic 
         String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"; 
         return Pattern.matches(emailRegex, email); 
     } 
  
     @Override 
     public boolean equals(Object o) { 
         if (this == o) return true; 
         if (o == null || getClass() != o.getClass()) return false; 
         DomainContactInfoValue that = (DomainContactInfoValue) o; 
         return Objects.equals(phone, that.phone) && Objects.equals(email, that.email); 
     } 
  
     @Override 
     public int hashCode() { 
         return Objects.hash(phone, email); 
     } 
  
     @Override 
     public String toString() { 
         return "DomainContactInfoValue{" + 
                 "phone='" + phone + '\'' + 
                 ", email='" + email + '\'' + 
                 '}'; 
     } 
 } 
 