package ai.shreds.domain;

import java.util.UUID;

/**
 * DomainProductRepositoryPort defines the methods for interacting with the Product entity in the database.
 */
public interface DomainProductRepositoryPort {
    /**
     * Finds a product by its unique identifier.
     *
     * @param id the unique identifier of the product
     * @return the product entity
     */
    DomainProductEntity findById(UUID id);

    /**
     * Saves or updates a product entity in the database.
     *
     * @param product the product entity to be saved or updated
     * @return the saved or updated product entity
     */
    DomainProductEntity save(DomainProductEntity product);

    /**
     * Finds inventory details by the product's unique identifier.
     *
     * @param productId the unique identifier of the product
     * @return the inventory entity
     */
    DomainInventoryEntity findByProductId(UUID productId);

    /**
     * Saves or updates an inventory entity in the database.
     *
     * @param inventory the inventory entity to be saved or updated
     */
    void saveInventory(DomainInventoryEntity inventory);
}