package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Builder; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
 import java.io.Serializable; 
  
 /** 
  * Data Transfer Object (DTO) representing a category in the application. 
  * This class is used to transfer category data between different layers of the application, 
  * ensuring that domain entities are not exposed directly. 
  */ 
 @Data 
 @Builder 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class SharedCategoryDTO implements Serializable { 
     private static final long serialVersionUID = 1L; 
     private Long id; 
     private String name; 
     private Long parentId; 
 }