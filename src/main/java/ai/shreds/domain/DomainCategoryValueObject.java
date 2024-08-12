package ai.shreds.domain; 
  
 import java.util.Objects; 
  
 public final class DomainCategoryValueObject { 
     private final String value; 
  
     public DomainCategoryValueObject(String value) { 
         if (value == null || value.trim().isEmpty()) { 
             throw new IllegalArgumentException("Value cannot be null or empty"); 
         } 
         this.value = value; 
     } 
  
     public String getValue() { 
         return value; 
     } 
  
     @Override 
     public boolean equals(Object o) { 
         if (this == o) return true; 
         if (o == null || getClass() != o.getClass()) return false; 
         DomainCategoryValueObject that = (DomainCategoryValueObject) o; 
         return Objects.equals(value, that.value); 
     } 
  
     @Override 
     public int hashCode() { 
         return Objects.hash(value); 
     } 
  
     @Override 
     public String toString() { 
         return "DomainCategoryValueObject{" + 
                 "value='" + value + '\'' + 
                 '}'; 
     } 
 } 
 // Note: Consider using Lombok annotations like @Getter, @EqualsAndHashCode, and @ToString to reduce boilerplate code.