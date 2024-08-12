package ai.shreds.infrastructure;

import ai.shreds.domain.DomainProductEntity;
import ai.shreds.domain.DomainProductRepositoryPort;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.transaction.Transactional;
import java.util.UUID;

/**
 * Implementation of the DomainProductRepositoryPort interface.
 * This class is responsible for persisting DomainProductEntity objects into the database.
 */
@Repository
public class InfrastructureProductRepositoryImpl implements DomainProductRepositoryPort {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureProductRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Saves a DomainProductEntity object to the database.
     *
     * @param product the product entity to be saved
     */
    @Override
    @Transactional
    public void save(DomainProductEntity product) {
        try {
            entityManager.persist(product);
            logger.info("Product saved successfully: {}", product);
        } catch (Exception e) {
            logger.error("Failed to save product: {}", product, e);
            throw new ProductPersistenceException("Failed to save product", e);
        }
    }

    /**
     * Checks if a product exists by name and category ID.
     *
     * @param name the name of the product
     * @param categoryId the UUID of the category
     * @return true if the product exists, false otherwise
     */
    public boolean existsByNameAndCategoryId(String name, UUID categoryId) {
        try {
            Long count = entityManager.createQuery(
                    "SELECT COUNT(p) FROM DomainProductEntity p WHERE p.name = :name AND p.categoryId = :categoryId",
                    Long.class)
                    .setParameter("name", name)
                    .setParameter("categoryId", categoryId)
                    .getSingleResult();
            return count > 0;
        } catch (Exception e) {
            logger.error("Failed to check existence of product with name: {} and categoryId: {}", name, categoryId, e);
            throw new ProductPersistenceException("Failed to check existence of product", e);
        }
    }
}

/**
 * Custom exception class for handling product persistence errors.
 */
class ProductPersistenceException extends RuntimeException {
    public ProductPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}