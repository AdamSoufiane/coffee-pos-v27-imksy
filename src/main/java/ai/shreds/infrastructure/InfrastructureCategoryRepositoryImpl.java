package ai.shreds.infrastructure; 
  
 import ai.shreds.domain.DomainCategoryEntity; 
 import ai.shreds.domain.DomainCategoryRepositoryPort; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Repository; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 import javax.persistence.EntityManager; 
 import javax.persistence.PersistenceContext; 
 import javax.transaction.Transactional; 
 import java.util.List; 
 import java.util.Optional; 
  
 @Repository 
 public class InfrastructureCategoryRepositoryImpl implements DomainCategoryRepositoryPort { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureCategoryRepositoryImpl.class); 
  
     @PersistenceContext 
     private EntityManager entityManager; 
  
     @Override 
     @Transactional 
     public DomainCategoryEntity save(DomainCategoryEntity category) { 
         validateCategory(category); 
         try { 
             if (category.getId() == null) { 
                 entityManager.persist(category); 
                 logger.info("Category persisted: {}", category); 
                 return category; 
             } else { 
                 DomainCategoryEntity updatedCategory = entityManager.merge(category); 
                 logger.info("Category merged: {}", updatedCategory); 
                 return updatedCategory; 
             } 
         } catch (Exception e) { 
             logger.error("Error saving category: {}", category, e); 
             throw e; 
         } 
     } 
  
     @Override 
     public Optional<DomainCategoryEntity> findById(Long id) { 
         validateId(id); 
         try { 
             Optional<DomainCategoryEntity> category = Optional.ofNullable(entityManager.find(DomainCategoryEntity.class, id)); 
             logger.info("Category found by id {}: {}", id, category); 
             return category; 
         } catch (Exception e) { 
             logger.error("Error finding category by id: {}", id, e); 
             throw e; 
         } 
     } 
  
     @Override 
     @Transactional 
     public void deleteById(Long id) { 
         validateId(id); 
         try { 
             DomainCategoryEntity category = entityManager.find(DomainCategoryEntity.class, id); 
             if (category != null) { 
                 entityManager.remove(category); 
                 logger.info("Category deleted: {}", category); 
             } else { 
                 logger.warn("Category not found for deletion by id: {}", id); 
             } 
         } catch (Exception e) { 
             logger.error("Error deleting category by id: {}", id, e); 
             throw e; 
         } 
     } 
  
     @Override 
     public List<DomainCategoryEntity> findAllByParentId(Long parentId) { 
         validateId(parentId); 
         try { 
             List<DomainCategoryEntity> categories = entityManager.createQuery("SELECT c FROM DomainCategoryEntity c WHERE c.parentId = :parentId", DomainCategoryEntity.class) 
                     .setParameter("parentId", parentId) 
                     .getResultList(); 
             logger.info("Categories found by parentId {}: {}", parentId, categories); 
             return categories; 
         } catch (Exception e) { 
             logger.error("Error finding categories by parentId: {}", parentId, e); 
             throw e; 
         } 
     } 
  
     @Override 
     public List<DomainCategoryEntity> findAll() { 
         try { 
             List<DomainCategoryEntity> categories = entityManager.createQuery("SELECT c FROM DomainCategoryEntity c", DomainCategoryEntity.class) 
                     .getResultList(); 
             logger.info("All categories found: {}", categories); 
             return categories; 
         } catch (Exception e) { 
             logger.error("Error finding all categories", e); 
             throw e; 
         } 
     } 
  
     private void validateCategory(DomainCategoryEntity category) { 
         if (category == null) { 
             throw new IllegalArgumentException("Category cannot be null"); 
         } 
         if (category.getName() == null || category.getName().isEmpty()) { 
             throw new IllegalArgumentException("Category name cannot be null or empty"); 
         } 
     } 
  
     private void validateId(Long id) { 
         if (id == null) { 
             throw new IllegalArgumentException("ID cannot be null"); 
         } 
     } 
 } 
 