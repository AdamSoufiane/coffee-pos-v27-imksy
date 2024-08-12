package ai.shreds.domain;

import java.util.UUID;

/**
 * DomainProductRepositoryPort is an interface that provides an abstraction for database operations related to the Product entity.
 * This ensures that the application layer can interact with the database without being tightly coupled to any specific database implementation.
 */
public interface DomainProductRepositoryPort {
    /**
     * Saves a given product entity to the database.
     *
     * @param product the product entity to be saved
     */
    void save(DomainProductEntity product);

    /**
     * Checks if a product exists by its name and category ID.
     *
     * @param name the name of the product
     * @param categoryId the UUID of the category
     * @return true if a product with the given name and category ID exists, false otherwise
     */
    boolean existsByNameAndCategoryId(String name, UUID categoryId);
}