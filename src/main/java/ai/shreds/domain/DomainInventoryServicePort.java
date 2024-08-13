package ai.shreds.domain;

import java.util.UUID;
import ai.shreds.domain.DomainProductEntity;
import ai.shreds.domain.InventoryUpdateException;
import ai.shreds.domain.DomainInventoryEntity;
import ai.shreds.domain.InventoryNotFoundException;
import ai.shreds.domain.InvalidUUIDFormatException;

/**
 * Interface for inventory-related operations in the domain layer.
 */
public interface DomainInventoryServicePort {
    /**
     * Updates the inventory details based on the provided DomainProductEntity.
     *
     * @param product the product entity containing inventory details to be updated
     * @throws InventoryUpdateException if there is an error during the update operation
     */
    void updateInventory(DomainProductEntity product) throws InventoryUpdateException;

    /**
     * Retrieves the inventory details for a specific product identified by its UUID.
     *
     * @param id the unique identifier of the product
     * @return the inventory entity associated with the given product ID
     * @throws InventoryNotFoundException if no inventory is found for the given product ID
     */
    DomainInventoryEntity getInventoryByProductId(UUID id) throws InventoryNotFoundException;

    /**
     * Validates the UUID format for the given product ID.
     *
     * @param id the unique identifier of the product
     * @throws InvalidUUIDFormatException if the UUID format is invalid
     */
    void validateUUIDFormat(UUID id) throws InvalidUUIDFormatException;
}

// Implementation note: Use Lombok annotations for getter and setter methods in DomainProductEntity and DomainInventoryEntity.
// Implementation note: Use transactions for methods that update the inventory to ensure atomicity.