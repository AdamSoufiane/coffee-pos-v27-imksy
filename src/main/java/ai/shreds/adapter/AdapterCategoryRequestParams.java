package ai.shreds.adapter; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 /** 
  * Data transfer object for category request parameters. 
  */ 
 @Data 
 @NoArgsConstructor 
 @AllArgsConstructor 
 public class AdapterCategoryRequestParams { 
     /** 
      * Name of the category. 
      */ 
     private String name; 
  
     /** 
      * Identifier of the parent category (optional). 
      */ 
     private Long parent_id; 
 }