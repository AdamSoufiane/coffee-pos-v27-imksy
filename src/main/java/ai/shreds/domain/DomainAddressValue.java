package ai.shreds.domain; 
  
 import lombok.EqualsAndHashCode; 
 import lombok.Getter; 
 import lombok.ToString; 
  
 @Getter 
 @EqualsAndHashCode 
 @ToString 
 public class DomainAddressValue { 
     private final String address; 
     private final String zip_code; 
     private final String city; 
  
     public DomainAddressValue(String address, String zip_code, String city) { 
         if(address == null || address.isEmpty() || zip_code == null || zip_code.isEmpty() || city == null || city.isEmpty()) { 
             throw new IllegalArgumentException("Address, zip code, and city must not be null or empty"); 
         } 
         this.address = address; 
         this.zip_code = zip_code; 
         this.city = city; 
     } 
 }