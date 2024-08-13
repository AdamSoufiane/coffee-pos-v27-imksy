package ai.shreds.infrastructure;

import ai.shreds.domain.DomainInventoryEntity;
import ai.shreds.domain.DomainInventoryServicePort;
import ai.shreds.domain.DomainProductEntity;
import ai.shreds.domain.DomainInventoryRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@Service
public class InfrastructureInventoryServiceClient implements DomainInventoryServicePort {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureInventoryServiceClient.class);
    private final DomainInventoryRepositoryPort inventoryRepository;

    @Autowired
    public InfrastructureInventoryServiceClient(DomainInventoryRepositoryPort inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void updateInventory(DomainProductEntity product) {
        // Log entry
        logger.info("Updating inventory for product: {}", product.getId());

        // Validate if product exists
        if (product == null || product.getId() == null) {
            logger.error("Invalid product data provided.");
            throw new IllegalArgumentException("Product data is invalid.");
        }

        // Retrieve the current inventory details for the product
        DomainInventoryEntity inventory = inventoryRepository.findByProductId(product.getId());
        if (inventory == null) {
            logger.error("No inventory found for product id: {}", product.getId());
            throw new IllegalArgumentException("No inventory found for the given product id.");
        }

        // Update the inventory details based on the product changes
        inventory.setAvailableQuantity(product.getStockQuantity());
        // You can add more logic here if needed

        // Save the updated inventory details back to the repository
        inventoryRepository.update(inventory);

        // Log exit
        logger.info("Inventory updated successfully for product: {}", product.getId());
    }

    @Override
    public DomainInventoryEntity getInventoryByProductId(UUID productId) {
        // Log entry
        logger.info("Retrieving inventory for product id: {}", productId);

        // Retrieve and return the inventory details for the specified product
        DomainInventoryEntity inventory = inventoryRepository.findByProductId(productId);
        if (inventory == null) {
            logger.error("No inventory found for product id: {}", productId);
            throw new IllegalArgumentException("No inventory found for the given product id.");
        }

        // Log exit
        logger.info("Inventory retrieved successfully for product id: {}", productId);
        return inventory;
    }

    @Override
    public void validateUUIDFormat(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("UUID cannot be null.");
        }
        // Additional validation logic can be added here if needed
    }
}