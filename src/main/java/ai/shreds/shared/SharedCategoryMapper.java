package ai.shreds.shared;

import ai.shreds.domain.DomainCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mapper interface for converting between DomainCategoryEntity and SharedCategoryDTO.
 */
@Mapper(componentModel = "spring")
public interface SharedCategoryMapper {
    SharedCategoryMapper INSTANCE = Mappers.getMapper(SharedCategoryMapper.class);

    Logger logger = LoggerFactory.getLogger(SharedCategoryMapper.class);

    /**
     * Converts a DomainCategoryEntity to a SharedCategoryDTO.
     *
     * @param domainCategory the domain category entity
     * @return the shared category DTO
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "parentId", target = "parentId")
    SharedCategoryDTO toSharedCategoryDTO(DomainCategoryEntity domainCategory);

    /**
     * Converts a SharedCategoryDTO to a DomainCategoryEntity.
     *
     * @param sharedCategory the shared category DTO
     * @return the domain category entity
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "parentId", target = "parentId")
    DomainCategoryEntity toDomainCategoryEntity(SharedCategoryDTO sharedCategory);

    /**
     * Safely converts a DomainCategoryEntity to a SharedCategoryDTO, handling null values.
     *
     * @param domainCategory the domain category entity
     * @return the shared category DTO or null if input is null
     */
    default SharedCategoryDTO toSharedCategoryDTOSafe(DomainCategoryEntity domainCategory) {
        if (domainCategory == null) {
            logger.warn("Attempted to convert a null DomainCategoryEntity to SharedCategoryDTO");
            return null;
        }
        return toSharedCategoryDTO(domainCategory);
    }

    /**
     * Safely converts a SharedCategoryDTO to a DomainCategoryEntity, handling null values.
     *
     * @param sharedCategory the shared category DTO
     * @return the domain category entity or null if input is null
     */
    default DomainCategoryEntity toDomainCategoryEntitySafe(SharedCategoryDTO sharedCategory) {
        if (sharedCategory == null) {
            logger.warn("Attempted to convert a null SharedCategoryDTO to DomainCategoryEntity");
            return null;
        }
        return toDomainCategoryEntity(sharedCategory);
    }
}