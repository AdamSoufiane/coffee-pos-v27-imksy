package ai.shreds.application; 
  
 import ai.shreds.adapter.AdapterCategoryRequestParams; 
 import ai.shreds.adapter.AdapterCategoryResponseDTO; 
 import ai.shreds.domain.DomainCategoryEntity; 
 import ai.shreds.domain.DomainCategoryRepositoryPort; 
 import ai.shreds.domain.DomainCategoryServicePort; 
 import lombok.RequiredArgsConstructor; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.stereotype.Service; 
  
 import java.util.List; 
 import java.util.stream.Collectors; 
  
 @Service 
 @RequiredArgsConstructor 
 public class ApplicationCategoryService implements ApplicationCreateCategoryInputPort, ApplicationUpdateCategoryInputPort, ApplicationDeleteCategoryInputPort, ApplicationRetrieveCategoryHierarchyInputPort { 
     private final DomainCategoryRepositoryPort domainCategoryRepositoryPort; 
     private final DomainCategoryServicePort domainCategoryServicePort; 
     private final ApplicationCategoryMapper applicationCategoryMapper; 
     private static final Logger logger = LoggerFactory.getLogger(ApplicationCategoryService.class); 
  
     /** 
      * Creates a new category in the hierarchy. 
      * @param request the category details 
      * @return the created category details 
      */ 
     @Override 
     public AdapterCategoryResponseDTO createCategory(AdapterCategoryRequestParams request) { 
         if (request == null) { 
             throw new IllegalArgumentException("Request cannot be null"); 
         } 
         logger.info("Creating category with name: {} and parent_id: {}", request.getName(), request.getParent_id()); 
         DomainCategoryEntity categoryEntity = applicationCategoryMapper.toDomainDTO(request); 
         domainCategoryServicePort.validateCategoryData(categoryEntity); 
         DomainCategoryEntity savedEntity = domainCategoryRepositoryPort.save(categoryEntity); 
         return applicationCategoryMapper.toAdapterDTO(savedEntity); 
     } 
  
     /** 
      * Updates an existing category's details. 
      * @param id the category ID 
      * @param request the updated category details 
      * @return the updated category details 
      */ 
     @Override 
     public AdapterCategoryResponseDTO updateCategory(Long id, AdapterCategoryRequestParams request) { 
         if (id == null || request == null) { 
             throw new IllegalArgumentException("ID and request cannot be null"); 
         } 
         logger.info("Updating category with id: {}", id); 
         domainCategoryServicePort.checkCategoryExistence(id); 
         DomainCategoryEntity categoryEntity = applicationCategoryMapper.toDomainDTO(request); 
         categoryEntity.setId(id); 
         domainCategoryServicePort.validateCategoryData(categoryEntity); 
         DomainCategoryEntity updatedEntity = domainCategoryRepositoryPort.save(categoryEntity); 
         return applicationCategoryMapper.toAdapterDTO(updatedEntity); 
     } 
  
     /** 
      * Deletes a category from the hierarchy. 
      * @param id the category ID 
      */ 
     @Override 
     public void deleteCategory(Long id) { 
         if (id == null) { 
             throw new IllegalArgumentException("ID cannot be null"); 
         } 
         logger.info("Deleting category with id: {}", id); 
         domainCategoryServicePort.checkCategoryExistence(id); 
         domainCategoryRepositoryPort.deleteById(id); 
     } 
  
     /** 
      * Fetches the entire category hierarchy or a specific branch. 
      * @param parent_id the parent category ID 
      * @return the list of categories 
      */ 
     @Override 
     public List<AdapterCategoryResponseDTO> retrieveCategoryHierarchy(Long parent_id) { 
         logger.info("Retrieving category hierarchy starting from parent_id: {}", parent_id); 
         List<DomainCategoryEntity> categoryEntities = domainCategoryServicePort.buildCategoryHierarchy(parent_id); 
         return categoryEntities.stream() 
                 .map(applicationCategoryMapper::toAdapterDTO) 
                 .collect(Collectors.toList()); 
     } 
 }