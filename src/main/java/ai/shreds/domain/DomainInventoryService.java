package ai.shreds.domain;

import java.util.UUID;
import java.sql.Timestamp;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import javax.validation.constraints.NotNull;

@Slf4j
@RequiredArgsConstructor
@Service
public class DomainInventoryService implements DomainInventoryServicePort {

    private final DomainInventoryRepositoryPort inventoryRepository;

    @Override
    public void updateInventoryDetails(@NotNull DomainProductEntity product) {
        // Validate product data
        validateProductData(product);

        // Retrieve current inventory details
        try {
            DomainInventoryEntity currentInventory = inventoryRepository.findByProductId(product.getId());

            // Update inventory details based on product changes
            currentInventory.setAvailableQuantity(product.getStockQuantity());
            currentInventory.setLastChecked(new Timestamp(System.currentTimeMillis()));

            // Save updated inventory details
            inventoryRepository.update(currentInventory);
            log.info("Updated inventory details for product ID: {}", product.getId());
        } catch (Exception e) {
            log.error("Error updating inventory details for product ID: {}", product.getId(), e);
            throw new InventoryUpdateException("Failed to update inventory details", e);
        }
    }

    @Override
    public DomainInventoryEntity getInventoryDetailsForProduct(@NotNull UUID id) {
        // Validate product ID
        validateProductId(id);

        // Retrieve inventory details
        try {
            DomainInventoryEntity inventory = inventoryRepository.findByProductId(id);
            log.info("Retrieved inventory details for product ID: {}", id);
            return inventory;
        } catch (Exception e) {
            log.error("Error retrieving inventory details for product ID: {}", id, e);
            throw new InventoryRetrievalException("Failed to retrieve inventory details", e);
        }
    }

    private void validateProductData(DomainProductEntity product) {
        if (product == null || product.getId() == null) {
            throw new IllegalArgumentException("Invalid product data");
        }
    }

    private void validateProductId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Invalid product ID");
        }
    }

    public class InventoryUpdateException extends RuntimeException {
        public InventoryUpdateException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public class InventoryRetrievalException extends RuntimeException {
        public InventoryRetrievalException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}