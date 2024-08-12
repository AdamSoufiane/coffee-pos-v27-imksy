package ai.shreds.domain;

import ai.shreds.shared.SharedCategoryDTO;
import org.springframework.stereotype.Component;
import lombok.Builder;

/**
 * Mapper class for converting between DomainCategoryEntity and SharedCategoryDTO.
 */
@Component
public class DomainCategoryMapper {

    /**
     * Converts a SharedCategoryDTO to a DomainCategoryEntity.
     * 
     * @param sharedCategory the SharedCategoryDTO to convert
     * @return the converted DomainCategoryEntity
     */
    public DomainCategoryEntity toDomainCategoryEntity(SharedCategoryDTO sharedCategory) {
        if (sharedCategory == null) {
            return null;
        }
        return DomainCategoryEntity.builder()
                .id(sharedCategory.getId())
                .name(sharedCategory.getName())
                .parentId(sharedCategory.getParentId())
                .build();
    }

    /**
     * Converts a DomainCategoryEntity to a SharedCategoryDTO.
     * 
     * @param domainCategory the DomainCategoryEntity to convert
     * @return the converted SharedCategoryDTO
     */
    public SharedCategoryDTO toSharedCategoryDTO(DomainCategoryEntity domainCategory) {
        if (domainCategory == null) {
            return null;
        }
        return SharedCategoryDTO.builder()
                .id(domainCategory.getId())
                .name(domainCategory.getName())
                .parentId(domainCategory.getParentId())
                .build();
    }
}