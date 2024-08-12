package ai.shreds.domain;

import ai.shreds.adapter.AdapterProductRequestDTO;
import ai.shreds.shared.AdapterProductResponseDTO;
import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DomainProductMapper {

    /**
     * Converts AdapterProductRequestDTO to DomainProductEntity.
     * @param request the product request DTO
     * @return the domain product entity
     */
    public DomainProductEntity toDomain(AdapterProductRequestDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        // Validation checks
        if (request.getId() == null) {
            throw new IllegalArgumentException("Product ID is required");
        }
        if (request.getPrice() != null && request.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be a positive value");
        }
        if (request.getStock_quantity() != null && request.getStock_quantity() < 0) {
            throw new IllegalArgumentException("Stock quantity must be a non-negative integer");
        }

        DomainProductEntity product = new DomainProductEntity();
        product.setId(request.getId());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory_id(request.getCategory_id());
        product.setStock_quantity(request.getStock_quantity());
        return product;
    }

    /**
     * Converts DomainProductEntity to AdapterProductResponseDTO.
     * @param entity the domain product entity
     * @return the product response DTO
     */
    public AdapterProductResponseDTO toResponse(DomainProductEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        AdapterProductResponseDTO response = new AdapterProductResponseDTO();
        response.setStatus("success");
        response.setProduct_id(entity.getId());
        return response;
    }

    /**
     * Converts DomainInventoryEntity to AdapterProductResponseDTO.
     * @param entity the domain inventory entity
     * @return the product response DTO
     */
    public AdapterProductResponseDTO toInventory(DomainInventoryEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        AdapterProductResponseDTO response = new AdapterProductResponseDTO();
        response.setStatus("success");
        response.setProduct_id(entity.getProductId());
        return response;
    }
}