package ai.shreds.shared; 
  
 import lombok.EqualsAndHashCode; 
 import lombok.Getter; 
 import lombok.ToString; 
  
 @EqualsAndHashCode 
 @ToString 
 public class SharedStringValueObject { 
     @Getter 
     private final String value; 
  
     public SharedStringValueObject(String value) { 
         if (value == null || value.trim().isEmpty()) { 
             throw new IllegalArgumentException("Value cannot be null or empty"); 
         } 
         this.value = value; 
     } 
 } 
 