package ai.shreds.infrastructure;

import ai.shreds.domain.DomainProductEntity;
import ai.shreds.domain.DomainProductRepositoryPort;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}

/**
 * Custom exception class for handling product persistence errors.
 */
class ProductPersistenceException extends RuntimeException {
    public ProductPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}