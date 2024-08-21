package ai.shreds.shared; 
  
 import lombok.EqualsAndHashCode; 
 import lombok.Getter; 
 import lombok.ToString; 
  
 @Getter 
 @EqualsAndHashCode 
 @ToString 
 public class SharedTimestamp { 
     private final String timestamp; 
  
     public SharedTimestamp(String timestamp) { 
         if (timestamp == null || timestamp.isEmpty()) { 
             throw new IllegalArgumentException("Timestamp cannot be null or empty"); 
         } 
         this.timestamp = timestamp; 
     } 
 }