package ai.shreds.adapter;

import ai.shreds.domain.DomainProductEntity;
import ai.shreds.shared.AdapterProductResponseDTO;
import ai.shreds.shared.AdapterProductRequestDTO;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.util.UUID;

@Component
public class AdapterProductMapper {

    private static final Logger logger = LoggerFactory.getLogger(AdapterProductMapper.class);

    public DomainProductEntity toDomain(AdapterProductRequestDTO request) {
        if (request == null) {
            return null;
        }
        logger.info("Mapping AdapterProductRequestDTO to DomainProductEntity");
        validateRequest(request);
        return DomainProductEntity.builder()
                .id(request.getId() != null ? request.getId() : UUID.randomUUID())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .category_id(request.getCategory_id())
                .stock_quantity(request.getStock_quantity())
                .build();
    }

    public AdapterProductResponseDTO toResponse(DomainProductEntity entity) {
        if (entity == null) {
            return null;
        }
        logger.info("Mapping DomainProductEntity to AdapterProductResponseDTO");
        return AdapterProductResponseDTO.builder()
                .status("success")
                .product_id(entity.getId() != null ? entity.getId() : UUID.randomUUID())
                .message("Product mapped successfully")
                .build();
    }

    private void validateRequest(AdapterProductRequestDTO request) {
        if (request.getPrice() != null && request.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be a positive value.");
        }
        if (request.getStock_quantity() != null && request.getStock_quantity() < 0) {
            throw new IllegalArgumentException("Stock quantity must be a non-negative integer.");
        }
    }
}