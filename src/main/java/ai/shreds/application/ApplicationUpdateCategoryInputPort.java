package ai.shreds.application; 
  
 import ai.shreds.adapter.AdapterCategoryRequestParams; 
 import ai.shreds.adapter.AdapterCategoryResponseDTO; 
  
 /** 
  * Interface for updating an existing category's details. 
  */ 
 public interface ApplicationUpdateCategoryInputPort { 
  
     /** 
      * Updates an existing category's details. 
      * 
      * @param id The id of the category to be updated. 
      * @param request The new details for the category. 
      * @return The updated category. 
      */ 
     AdapterCategoryResponseDTO updateCategory(Long id, AdapterCategoryRequestParams request); 
 }