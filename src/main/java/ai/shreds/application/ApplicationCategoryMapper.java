package ai.shreds.application.mapper;

import ai.shreds.adapter.dto.AdapterCategoryResponseDTO;
import ai.shreds.domain.entity.DomainCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper interface for converting between DomainCategoryEntity and AdapterCategoryResponseDTO.
 */
@Mapper
public interface ApplicationCategoryMapper {
    ApplicationCategoryMapper INSTANCE = Mappers.getMapper(ApplicationCategoryMapper.class);

    /**
     * Converts DomainCategoryEntity to AdapterCategoryResponseDTO.
     *
     * @param domainCategoryEntity the domain category entity
     * @return the adapter category response DTO
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "parentId", target = "parent_id")
    AdapterCategoryResponseDTO toAdapterDTO(DomainCategoryEntity domainCategoryEntity);

    /**
     * Converts AdapterCategoryResponseDTO to DomainCategoryEntity.
     *
     * @param adapterCategoryResponseDTO the adapter category response DTO
     * @return the domain category entity
     */
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "parent_id", target = "parentId")
    DomainCategoryEntity toDomainDTO(AdapterCategoryResponseDTO adapterCategoryResponseDTO);

    /**
     * Handles mapping exceptions.
     *
     * @param e the exception
     */
    default void handleMappingException(Exception e) {
        // Handle the mapping exception appropriately
        throw new RuntimeException("Mapping error: " + e.getMessage(), e);
    }
}