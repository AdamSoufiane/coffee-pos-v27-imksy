package ai.shreds.application; 
  
 /** 
  * Interface for deleting a category in the application layer. 
  */ 
 public interface ApplicationDeleteCategoryInputPort { 
     /** 
      * Deletes a category given its unique identifier. 
      * 
      * @param id the unique identifier of the category to be deleted 
      * @throws CategoryNotFoundException if the category does not exist 
      */ 
     void deleteCategory(Long id) throws CategoryNotFoundException; 
 }