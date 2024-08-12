package ai.shreds.domain;

import ai.shreds.shared.SharedProductDTO;
import ai.shreds.shared.SharedRequestParams;
import ai.shreds.application.DomainProductRepositoryPort;
import ai.shreds.application.DomainInventoryRepositoryPort;
import ai.shreds.infrastructure.InfrastructureTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.math.BigDecimal;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class DomainCreateProductServiceDomain {

    private final DomainProductRepositoryPort productRepository;
    private final DomainInventoryRepositoryPort inventoryRepository;
    private final InfrastructureTransactionService transactionService;
    private static final Logger logger = Logger.getLogger(DomainCreateProductServiceDomain.class.getName());

    public void validateProductData(SharedRequestParams request) {
        if (request.getName() == null || request.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name is required.");
        }
        if (request.getPrice() == null || request.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price must be a positive value.");
        }
        if (request.getStockQuantity() == null || request.getStockQuantity() < 0) {
            throw new IllegalArgumentException("Stock quantity must be a non-negative integer.");
        }
        // Additional validation rules can be added here
        // Validate unique product name within its category
        if (productRepository.existsByNameAndCategoryId(request.getName(), request.getCategoryId())) {
            throw new IllegalArgumentException("Product name must be unique within its category.");
        }
    }

    public SharedProductDTO processProductCreation(SharedRequestParams request) {
        validateProductData(request);

        transactionService.startTransaction();
        try {
            UUID productId = UUID.randomUUID();
            DomainProductEntity product = new DomainProductEntity(productId, request.getName(), request.getDescription(), request.getPrice(), request.getCategoryId(), request.getStockQuantity());
            productRepository.save(product);

            DomainInventoryEntity inventory = new DomainInventoryEntity(productId, request.getStockQuantity(), 0);
            inventoryRepository.save(inventory);

            transactionService.commitTransaction();
            logger.info("Transaction committed successfully for product creation.");

            return new SharedProductDTO(productId, request.getName(), request.getDescription(), request.getPrice(), request.getCategoryId(), request.getStockQuantity());
        } catch (Exception e) {
            transactionService.rollbackTransaction();
            logger.severe("Transaction rolled back due to error: " + e.getMessage());
            throw new RuntimeException("Error creating product", e);
        }
    }
}