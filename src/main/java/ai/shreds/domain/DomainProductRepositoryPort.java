package ai.shreds.domain;

import java.util.UUID;

/**
 * Interface for Product Repository Port.
 * Provides methods for CRUD operations on Product entities.
 */
public interface DomainProductRepositoryPort {
    /**
     * Saves a product entity to the database.
     * @param entity the product entity to be saved
     */
    void save(DomainProductEntity entity);

    /**
     * Finds a product by its unique identifier.
     * @param id the unique identifier of the product
     * @return the product entity if found, otherwise null
     */
    DomainProductEntity findById(UUID id);

    /**
     * Deletes a product by its unique identifier.
     * @param id the unique identifier of the product
     */
    void deleteById(UUID id);
}