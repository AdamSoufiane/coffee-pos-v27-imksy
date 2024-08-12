package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 @Data 
 @AllArgsConstructor 
 @NoArgsConstructor 
 public class SharedCategoryDTO { 
     private Long id; 
     private String name; 
     private Long parentId; 
 } 
 