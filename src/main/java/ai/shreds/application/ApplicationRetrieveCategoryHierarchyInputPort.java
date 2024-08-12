package ai.shreds.application; 
  
 import ai.shreds.adapter.AdapterCategoryResponseDTO; 
 import java.util.List; 
  
 /** 
  * Interface for retrieving category hierarchy. 
  */ 
 @FunctionalInterface 
 public interface ApplicationRetrieveCategoryHierarchyInputPort { 
     /** 
      * Retrieves the category hierarchy starting from the specified parent_id. 
      * 
      * @param parent_id the ID of the parent category (nullable) 
      * @return a list of AdapterCategoryResponseDTO objects representing the category hierarchy 
      */ 
     List<AdapterCategoryResponseDTO> retrieveCategoryHierarchy(Long parent_id); 
 }