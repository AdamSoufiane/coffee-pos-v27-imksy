package ai.shreds.shared; 
  
 import lombok.EqualsAndHashCode; 
 import lombok.Getter; 
 import lombok.ToString; 
  
 /** 
  * SharedValueObject is a simple value object that encapsulates a single value. 
  * This class is immutable to ensure its integrity and thread-safety. 
  */ 
 @Getter 
 @EqualsAndHashCode 
 @ToString 
 public class SharedValueObject { 
     private final Type value; 
  
     public SharedValueObject(Type value) { 
         this.value = value; 
     } 
 } 
 