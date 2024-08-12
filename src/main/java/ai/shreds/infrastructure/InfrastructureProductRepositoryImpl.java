package ai.shreds.infrastructure;

import ai.shreds.domain.DomainProductEntity;
import ai.shreds.domain.DomainProductRepositoryPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

/**
 * Implementation of the DomainProductRepositoryPort interface.
 * Provides methods to interact with the database for Product entities.
 */
@Repository
@RequiredArgsConstructor
public class InfrastructureProductRepositoryImpl implements DomainProductRepositoryPort {

    @PersistenceContext
    private final EntityManager entityManager;

    /**
     * Finds a product by its unique identifier.
     * @param id the unique identifier of the product
     * @return the product entity
     */
    @Override
    public DomainProductEntity findById(UUID id) {
        try {
            return entityManager.find(DomainProductEntity.class, id);
        } catch (PersistenceException e) {
            throw new RuntimeException("Error finding product by ID", e);
        }
    }

    /**
     * Saves or updates a product entity in the database.
     * @param product the product entity to be saved or updated
     */
    @Override
    @Transactional
    public void save(DomainProductEntity product) {
        try {
            if (product.getId() == null) {
                entityManager.persist(product);
            } else {
                entityManager.merge(product);
            }
        } catch (PersistenceException e) {
            throw new RuntimeException("Error saving product", e);
        }
    }
}