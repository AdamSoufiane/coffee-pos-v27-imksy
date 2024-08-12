package ai.shreds.application;

import ai.shreds.adapter.AdapterProductRequestDTO;
import ai.shreds.adapter.AdapterProductResponseDTO;
import ai.shreds.domain.DomainProductEntity;
import ai.shreds.domain.DomainUpdateProductService;
import ai.shreds.domain.DomainProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationUpdateProductService implements ApplicationUpdateProductInputPort, ApplicationUpdateProductOutputPort {

    private final DomainUpdateProductService domainUpdateProductService;
    private final DomainProductMapper domainProductMapper;

    /**
     * Updates the details of an existing product.
     * @param request Product update request DTO
     * @return Response DTO with updated product details
     */
    @Override
    public AdapterProductResponseDTO updateProduct(AdapterProductRequestDTO request) {
        try {
            // Validate request data
            validateRequestData(request);

            // Log the incoming request
            log.info("Updating product with ID: {}", request.getId());

            // Map request to domain entity
            DomainProductEntity productEntity = domainProductMapper.toDomain(request);

            // Update product details using domain service
            DomainProductEntity updatedProduct = domainUpdateProductService.updateProduct(productEntity);

            // Map updated domain entity to response DTO
            return domainProductMapper.toResponse(updatedProduct);
        } catch (Exception e) {
            // Log the exception
            log.error("Error updating product", e);
            // Handle exception and return error response
            return new ApplicationProductServiceException().handleException(e);
        }
    }

    /**
     * Notifies other services about the product update.
     * @param response Response DTO with updated product details
     */
    @Override
    public void notifyProductUpdate(AdapterProductResponseDTO response) {
        try {
            // Log the notification
            log.info("Notifying product update for product ID: {}", extractProductId(response));

            // Produce Kafka message to notify other services about the product update
            domainUpdateProductService.notifyProductUpdate(response);
        } catch (Exception e) {
            // Log the exception
            log.error("Error notifying product update", e);
            // Handle exception
            new ApplicationProductServiceException().handleException(e);
        }
    }

    /**
     * Extracts the product ID from the response DTO.
     * @param response Response DTO
     * @return Product ID
     */
    private UUID extractProductId(AdapterProductResponseDTO response) {
        return response.getProductId();
    }

    /**
     * Validates the product update request data.
     * @param request Product update request DTO
     * @throws CustomValidationException if validation fails
     */
    private void validateRequestData(AdapterProductRequestDTO request) throws CustomValidationException {
        if (request.getId() == null) {
            throw new CustomValidationException("Product ID is required");
        }
        if (request.getPrice() != null && request.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new CustomValidationException("Product price must be positive");
        }
        if (request.getStockQuantity() != null && request.getStockQuantity() < 0) {
            throw new CustomValidationException("Stock quantity must be non-negative");
        }
    }
}