package ai.shreds.domain;

import java.util.UUID;

/**
 * Interface for CRUD operations on the Inventory entity.
 */
public interface DomainInventoryRepositoryPort {
    /**
     * Saves an inventory entity to the database.
     *
     * @param entity the inventory entity to save
     */
    void save(DomainInventoryEntity entity);

    /**
     * Finds inventory details by the product's unique identifier.
     *
     * @param productId the unique identifier of the product
     * @return the inventory details for the specified product
     */
    DomainInventoryEntity findByProductId(UUID productId);

    /**
     * Updates the inventory details for a specific product.
     *
     * @param entity the inventory entity with updated details
     */
    void update(DomainInventoryEntity entity);
}
// Implementation Note: Use Lombok annotations in the implementing classes to reduce boilerplate code.