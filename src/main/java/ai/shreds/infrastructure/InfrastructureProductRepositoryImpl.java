package ai.shreds.infrastructure;

import ai.shreds.domain.DomainProductEntity;
import ai.shreds.domain.DomainProductRepositoryPort;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
@RequiredArgsConstructor
@Slf4j
public class InfrastructureProductRepositoryImpl implements DomainProductRepositoryPort {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void save(DomainProductEntity entity) {
        try {
            entityManager.persist(entity);
            log.info("Product saved: {}", entity);
        } catch (Exception e) {
            log.error("Error saving product: {}", entity, e);
            throw new DatabaseOperationException("Error saving product", e);
        }
    }

    @Override
    public DomainProductEntity findById(UUID id) {
        try {
            DomainProductEntity entity = entityManager.find(DomainProductEntity.class, id);
            log.info("Product found: {}", entity);
            return entity;
        } catch (Exception e) {
            log.error("Error finding product with id: {}", id, e);
            throw new DatabaseOperationException("Error finding product", e);
        }
    }

    @Override
    public void deleteById(UUID id) {
        try {
            DomainProductEntity entity = findById(id);
            if (entity != null) {
                entityManager.remove(entity);
                log.info("Product deleted: {}", entity);
            } else {
                log.warn("Product with id: {} not found for deletion", id);
            }
        } catch (Exception e) {
            log.error("Error deleting product with id: {}", id, e);
            throw new DatabaseOperationException("Error deleting product", e);
        }
    }
}

class DatabaseOperationException extends RuntimeException {
    public DatabaseOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}