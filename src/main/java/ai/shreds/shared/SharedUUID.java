package ai.shreds.shared; 
  
 import java.util.Objects; 
 import java.util.UUID; 
 import lombok.Getter; 
  
 /** 
  * Value object representing a UUID. 
  */ 
 @Getter 
 public class SharedUUID { 
     private final String uuid; 
  
     /** 
      * Constructs a SharedUUID with the specified UUID string. 
      * 
      * @param uuid the UUID string 
      */ 
     public SharedUUID(String uuid) { 
         if (uuid == null || uuid.isEmpty()) { 
             throw new IllegalArgumentException("UUID cannot be null or empty"); 
         } 
         this.uuid = uuid; 
     } 
  
     /** 
      * Constructs a SharedUUID with the specified UUID. 
      * 
      * @param uuid the UUID 
      */ 
     public SharedUUID(UUID uuid) { 
         this(uuid.toString()); 
     } 
  
     @Override 
     public boolean equals(Object o) { 
         if (this == o) return true; 
         if (o == null || getClass() != o.getClass()) return false; 
         SharedUUID that = (SharedUUID) o; 
         return Objects.equals(uuid, that.uuid); 
     } 
  
     @Override 
     public int hashCode() { 
         return Objects.hash(uuid); 
     } 
  
     @Override 
     public String toString() { 
         return uuid; 
     } 
 } 
  
 /** 
  * Note: Include Javadoc comments for the class and methods to enhance code documentation. 
  */