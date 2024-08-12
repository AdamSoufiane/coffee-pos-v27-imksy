package ai.shreds.domain; 
  
 import ai.shreds.domain.DomainCategoryEntity; 
 import ai.shreds.domain.DomainCategoryRepositoryPort; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Repository; 
  
 import java.util.List; 
 import java.util.Optional; 
  
 @Repository 
 public class DomainCategoryRepositoryImpl implements DomainCategoryRepositoryPort { 
  
     private final SpringDataCategoryRepository springDataCategoryRepository; 
  
     @Autowired 
     public DomainCategoryRepositoryImpl(SpringDataCategoryRepository springDataCategoryRepository) { 
         this.springDataCategoryRepository = springDataCategoryRepository; 
     } 
  
     /** 
      * Finds a category by its unique identifier (id). 
      * @param id the unique identifier of the category. 
      * @return an Optional containing the DomainCategoryEntity if found, otherwise an empty Optional. 
      */ 
     @Override 
     public Optional<DomainCategoryEntity> findById(Long id) { 
         return springDataCategoryRepository.findById(id); 
     } 
  
     /** 
      * Retrieves all categories from the database. 
      * @return a list of DomainCategoryEntity objects. 
      */ 
     @Override 
     public List<DomainCategoryEntity> findAll() { 
         return springDataCategoryRepository.findAll(); 
     } 
  
     /** 
      * Finds all subcategories that have the given parentId. 
      * @param parentId the identifier of the parent category. 
      * @return a list of DomainCategoryEntity objects. 
      */ 
     @Override 
     public List<DomainCategoryEntity> findByParentId(Long parentId) { 
         return springDataCategoryRepository.findByParentId(parentId); 
     } 
 } 
  
 import org.springframework.data.jpa.repository.JpaRepository; 
 @Repository 
 interface SpringDataCategoryRepository extends JpaRepository<DomainCategoryEntity, Long> { 
     List<DomainCategoryEntity> findByParentId(Long parentId); 
 }