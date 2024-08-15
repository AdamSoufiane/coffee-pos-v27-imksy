package ai.shreds.domain; 
  
 import lombok.Getter; 
 import lombok.ToString; 
 import java.util.Objects; 
  
 /** 
  * DomainAddressValue is a value object in the domain layer that encapsulates the address details of a supplier. 
  * It contains three fields: address, zip_code, and city. The class is immutable, ensuring data consistency and integrity. 
  */ 
 @Getter 
 @ToString 
 public final class DomainAddressValue { 
     private final String address; 
     private final String zip_code; 
     private final String city; 
  
     /** 
      * Constructs a DomainAddressValue with the specified address details. 
      * 
      * @param address   the physical address of the supplier 
      * @param zip_code  the postal code associated with the supplier's address 
      * @param city      the city where the supplier is located 
      * @throws IllegalArgumentException if any of the parameters are null or empty 
      */ 
     public DomainAddressValue(String address, String zip_code, String city) { 
         if (address == null || address.isEmpty()) { 
             throw new IllegalArgumentException("Address cannot be null or empty"); 
         } 
         if (zip_code == null || zip_code.isEmpty()) { 
             throw new IllegalArgumentException("Zip code cannot be null or empty"); 
         } 
         if (city == null || city.isEmpty()) { 
             throw new IllegalArgumentException("City cannot be null or empty"); 
         } 
         this.address = address; 
         this.zip_code = zip_code; 
         this.city = city; 
     } 
  
     @Override 
     public boolean equals(Object o) { 
         if (this == o) return true; 
         if (o == null || getClass() != o.getClass()) return false; 
         DomainAddressValue that = (DomainAddressValue) o; 
         return Objects.equals(address, that.address) && 
                 Objects.equals(zip_code, that.zip_code) && 
                 Objects.equals(city, that.city); 
     } 
  
     @Override 
     public int hashCode() { 
         return Objects.hash(address, zip_code, city); 
     } 
 }