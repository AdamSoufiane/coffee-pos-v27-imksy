package ai.shreds.adapter;

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
                adapterDTO.getParentId(),
                null, // Assuming parent is not set here
                null  // Assuming children are not set here
        );
    }

    /**
     * Converts an AdapterCategoryRequestParams to a DomainCategoryEntity.
     * @param adapterDTO the adapter category request params
     * @return the domain category entity
     */
    public DomainCategoryEntity toDomainEntity(AdapterCategoryRequestParams adapterDTO) {
        if (adapterDTO == null) {
            return null;
        }
        return new DomainCategoryEntity(
                null, // Assuming ID is generated by the database
                adapterDTO.getName(),
                adapterDTO.getParentId(),
                null, // Assuming parent is not set here
                null  // Assuming children are not set here
        );
    }
}