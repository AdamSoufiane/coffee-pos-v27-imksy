package ai.shreds.adapter;

import ai.shreds.adapter.AdapterCategoryResponseDTO;
import ai.shreds.domain.DomainCategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class AdapterCategoryMapper {

    /**
     * Converts a DomainCategoryEntity to an AdapterCategoryResponseDTO.
     * @param domainDTO the domain category entity
     * @return the adapter category response DTO
     */
    public AdapterCategoryResponseDTO toAdapterDTO(DomainCategoryEntity domainDTO) {
        if (domainDTO == null) {
            return null;
        }
        return new AdapterCategoryResponseDTO(
                domainDTO.getId(),
                domainDTO.getName(),
                domainDTO.getParentId()
        );
    }

    /**
     * Converts an AdapterCategoryResponseDTO to a DomainCategoryEntity.
     * @param adapterDTO the adapter category response DTO
     * @return the domain category entity
     */
    public DomainCategoryEntity toDomainDTO(AdapterCategoryResponseDTO adapterDTO) {
        if (adapterDTO == null) {
            return null;
        }
        return new DomainCategoryEntity(
                adapterDTO.getId(),
                adapterDTO.getName(),
                adapterDTO.getParentId()
        );
    }
}