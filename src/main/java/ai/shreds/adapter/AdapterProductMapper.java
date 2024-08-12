package ai.shreds.adapter;

import ai.shreds.shared.AdapterProductRequestDTO;
import ai.shreds.shared.AdapterProductResponseDTO;
import ai.shreds.domain.DomainProductEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.util.UUID;

@Component
public class AdapterProductMapper {

    private static final Logger logger = LoggerFactory.getLogger(AdapterProductMapper.class);
    private AdapterProductMapper() {} // Private constructor to enforce the use of @Component annotation

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
                .categoryId(request.getCategoryId())
                .stockQuantity(request.getStockQuantity())
                .build();
    }

    public AdapterProductResponseDTO toResponse(DomainProductEntity entity) {
        if (entity == null) {
            return null;
        }
        logger.info("Mapping DomainProductEntity to AdapterProductResponseDTO");
        return AdapterProductResponseDTO.builder()
                .status("success")
                .productId(entity.getId() != null ? entity.getId() : UUID.randomUUID())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .categoryId(entity.getCategoryId())
                .stockQuantity(entity.getStockQuantity())
                .build();
    }

    private void validateRequest(AdapterProductRequestDTO request) {
        if (request.getPrice() != null && request.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be a positive value.");
        }
        if (request.getStockQuantity() != null && request.getStockQuantity() < 0) {
            throw new IllegalArgumentException("Stock quantity must be a non-negative integer.");
        }
    }
}