package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class SharedRequestParams { 
     private String param1; 
     private String param2; 
 } 
 // Note: Use Lombok annotations for getters and setters if not already present.