package ai.shreds.adapter;

import ai.shreds.domain.DomainCategoryEntity;
import ai.shreds.adapter.AdapterCategoryRequestParams;
import ai.shreds.adapter.AdapterCategoryResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class AdapterCategoryMapper {

    /**
     * Maps DomainCategoryEntity to AdapterCategoryResponseDTO.
     * @param domainCategory the domain category entity
     * @return the adapter category response DTO
     */
    public AdapterCategoryResponseDTO toAdapterCategoryResponseDTO(DomainCategoryEntity domainCategory) {
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
    public DomainCategoryEntity toDomainCategoryEntity(AdapterCategoryRequestParams adapterCategory) {
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