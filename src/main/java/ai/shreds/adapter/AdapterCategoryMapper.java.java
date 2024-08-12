package ai.shreds.adapter;

import ai.shreds.shared.SharedCategoryDTO;
import ai.shreds.adapter.AdapterCategoryResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class AdapterCategoryMapper {

    /**
     * Maps SharedCategoryDTO to AdapterCategoryResponseDTO.
     * @param sharedCategoryDTO the shared category DTO
     * @return the adapter category response DTO
     */
    public AdapterCategoryResponseDTO toAdapterCategoryResponseDTO(SharedCategoryDTO sharedCategoryDTO) {
        if (sharedCategoryDTO == null) {
            return null;
        }
        return new AdapterCategoryResponseDTO(
                sharedCategoryDTO.getId(),
                sharedCategoryDTO.getName(),
                sharedCategoryDTO.getParentId()
        );
    }
}