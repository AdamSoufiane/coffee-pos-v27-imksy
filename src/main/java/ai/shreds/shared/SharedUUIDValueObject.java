package ai.shreds.shared; 
  
 import lombok.Getter; 
 import lombok.RequiredArgsConstructor; 
  
 import java.util.Objects; 
 import java.util.UUID; 
  
 @Getter 
 @RequiredArgsConstructor 
 public class SharedUUIDValueObject { 
     private final String uuid; 
  
     public SharedUUIDValueObject(String uuid) { 
         Objects.requireNonNull(uuid, "UUID cannot be null"); 
         try { 
             UUID.fromString(uuid); 
         } catch (IllegalArgumentException e) { 
             throw new IllegalArgumentException("Invalid UUID format", e); 
         } 
         this.uuid = uuid; 
     } 
  
     @Override 
     public boolean equals(Object o) { 
         if (this == o) return true; 
         if (o == null || getClass() != o.getClass()) return false; 
         SharedUUIDValueObject that = (SharedUUIDValueObject) o; 
         return uuid.equals(that.uuid); 
     } 
  
     @Override 
     public int hashCode() { 
         return Objects.hash(uuid); 
     } 
  
     @Override 
     public String toString() { 
         return "SharedUUIDValueObject{" + 
                 "uuid='" + uuid + '\'' + 
                 '}'; 
     } 
 }