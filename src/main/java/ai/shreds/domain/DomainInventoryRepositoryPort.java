package ai.shreds.domain;

import java.util.UUID;

/**
 * Interface for interacting with the inventory data in the database.
 */
public interface DomainInventoryRepositoryPort {
    /**
     * Finds inventory details by the product's unique identifier.
     *
     * @param productId the unique identifier of the product
     * @return the inventory details for the specified product
     */
    DomainInventoryEntity findByProductId(UUID productId);

    /**
     * Saves or updates the inventory details in the database.
     *
     * @param inventory the inventory entity to be saved or updated
     */
    void save(DomainInventoryEntity inventory);
}