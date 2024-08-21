package ai.shreds.shared; 
  
 import lombok.Value; 
  
 /** 
  * Value object representing an integer value. 
  * This class is immutable and uses Lombok annotations to reduce boilerplate code. 
  */ 
 @Value 
 public class SharedIntegerValueObject { 
     Integer value; 
  
     public SharedIntegerValueObject(Integer value) { 
         if (value == null) { 
             throw new IllegalArgumentException("Value cannot be null"); 
         } 
         if (value < 0) { // Example validation for non-negative values 
             throw new IllegalArgumentException("Value cannot be negative"); 
         } 
         this.value = value; 
     } 
  
     @Override 
     public String toString() { 
         return value.toString(); 
     } 
 } 
 