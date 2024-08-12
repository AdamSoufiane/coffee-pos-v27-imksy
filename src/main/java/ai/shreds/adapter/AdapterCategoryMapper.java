package ai.shreds.adapter; 
  
 import ai.shreds.domain.DomainCategoryEntity; 
 import org.springframework.stereotype.Component; 
 import lombok.Getter; 
 import lombok.Setter; 
 import lombok.AllArgsConstructor; 
  
 @Component 
 public class AdapterCategoryMapper { 
  
     /** 
      * Maps DomainCategoryEntity to AdapterCategoryResponseDTO. 
      * @param domainCategory the domain category entity 
      * @return the adapter category response DTO 
      */ 
     public AdapterCategoryResponseDTO mapToAdapterCategoryResponseDTO(DomainCategoryEntity domainCategory) { 
         if (domainCategory == null) { 
             return null; 
         } 
         return new AdapterCategoryResponseDTO( 
                 domainCategory.getId(), 
                 domainCategory.getName(), 
                 domainCategory.getParentId() 
         ); 
     } 
  
     /** 
      * Maps AdapterCategoryRequestParams to DomainCategoryEntity. 
      * @param adapterCategory the adapter category request params 
      * @return the domain category entity 
      */ 
     public DomainCategoryEntity mapToDomainCategoryEntity(AdapterCategoryRequestParams adapterCategory) { 
         if (adapterCategory == null) { 
             return null; 
         } 
         return new DomainCategoryEntity( 
                 adapterCategory.getId(), 
                 adapterCategory.getName(), 
                 adapterCategory.getParent_id() 
         ); 
     } 
 } 
  
 @Getter 
 @Setter 
 @AllArgsConstructor 
 class AdapterCategoryResponseDTO { 
     private Long id; 
     private String name; 
     private Long parent_id; 
 } 
  
 @Getter 
 @Setter 
 @AllArgsConstructor 
 class AdapterCategoryRequestParams { 
     private Long id; 
     private Long parent_id; 
     private String name; 
 } 
  
 @Getter 
 @Setter 
 @AllArgsConstructor 
 class DomainCategoryEntity { 
     private Long id; 
     private String name; 
     private Long parentId; 
 }