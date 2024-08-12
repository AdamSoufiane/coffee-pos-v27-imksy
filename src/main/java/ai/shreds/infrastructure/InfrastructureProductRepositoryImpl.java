package ai.shreds.infrastructure;

import ai.shreds.domain.DomainProductEntity;
import ai.shreds.domain.DomainProductRepositoryPort;
import ai.shreds.domain.DomainInventoryEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;
import java.util.UUID;

/**
 * Implementation of the DomainProductRepositoryPort interface.
 * Provides methods to interact with the database for Product entities.
 */
@Repository
@RequiredArgsConstructor
public class InfrastructureProductRepositoryImpl implements DomainProductRepositoryPort {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Finds a product by its unique identifier.
     * @param id the unique identifier of the product
     * @return the product entity
     */
    @Override
    public DomainProductEntity findById(UUID id) {
        try {
            return entityManager.find(DomainProductEntity.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Error finding product by ID", e);
        }
    }

    /**
     * Saves or updates a product entity in the database.
     * @param product the product entity to be saved or updated
     * @return the saved or updated product entity
     */
    @Override
    @Transactional
    public DomainProductEntity save(DomainProductEntity product) {
        try {
            if (product.getId() == null) {
                entityManager.persist(product);
                return product;
            } else {
                return entityManager.merge(product);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error saving product", e);
        }
    }

    /**
     * Finds inventory details by the product's unique identifier.
     * @param productId the unique identifier of the product
     * @return the inventory entity
     */
    @Override
    public DomainInventoryEntity findByProductId(UUID productId) {
        try {
            return entityManager.find(DomainInventoryEntity.class, productId);
        } catch (Exception e) {
            throw new RuntimeException("Error finding inventory by product ID", e);
        }
    }

    /**
     * Saves or updates an inventory entity in the database.
     * @param inventory the inventory entity to be saved or updated
     */
    @Override
    @Transactional
    public void saveInventory(DomainInventoryEntity inventory) {
        try {
            if (inventory.getProductId() == null) {
                entityManager.persist(inventory);
            } else {
                entityManager.merge(inventory);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error saving inventory", e);
        }
    }
}