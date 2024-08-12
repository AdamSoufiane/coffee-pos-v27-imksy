package ai.shreds.application;

import ai.shreds.shared.AdapterProductRequestDTO;
import ai.shreds.shared.AdapterProductResponseDTO;
import ai.shreds.domain.DomainProductEntity;
import ai.shreds.domain.DomainUpdateProductService;
import ai.shreds.domain.DomainProductMapper;
import ai.shreds.exceptions.CustomValidationException;
import ai.shreds.exceptions.ApplicationProductServiceException;
import ai.shreds.domain.DomainProductServiceException;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationUpdateProductService implements ApplicationUpdateProductInputPort, ApplicationUpdateProductOutputPort {

    private final DomainUpdateProductService domainUpdateProductService;
    private final DomainProductMapper domainProductMapper;

    private static final Logger log = LoggerFactory.getLogger(ApplicationUpdateProductService.class);

    @Override
    public AdapterProductResponseDTO updateProduct(AdapterProductRequestDTO request) {
        try {
            validateRequestData(request);
            log.info("Updating product with ID: {}", request.getId());
            DomainProductEntity productEntity = domainProductMapper.toDomain(request);
            DomainProductEntity updatedProduct = domainUpdateProductService.updateProduct(productEntity);
            return domainProductMapper.toResponse(updatedProduct);
        } catch (Exception e) {
            log.error("Error updating product", e);
            return new DomainProductServiceException().handleException(e);
        }
    }

    @Override
    public void notifyProductUpdate(AdapterProductResponseDTO response) {
        try {
            log.info("Notifying product update for product ID: {}", extractProductId(response));
            domainUpdateProductService.notifyProductUpdate(response);
        } catch (Exception e) {
            log.error("Error notifying product update", e);
            new ApplicationProductServiceException().handleException(e);
        }
    }

    private UUID extractProductId(AdapterProductResponseDTO response) {
        return response.getProductId();
    }

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