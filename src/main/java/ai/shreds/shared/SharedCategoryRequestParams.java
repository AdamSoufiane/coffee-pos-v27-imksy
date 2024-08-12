package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 /** 
  * Data Transfer Object for encapsulating request parameters for category-related operations. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class SharedCategoryRequestParams { 
     /** 
      * Unique identifier of the category. 
      */ 
     private Long id; 
  
     /** 
      * Identifier of the parent category. 
      */ 
     private Long parentId; 
 } 
 