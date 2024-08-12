package ai.shreds.infrastructure;

import ai.shreds.domain.DomainCategoryEntity;
import ai.shreds.domain.DomainCategoryRepositoryPort;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class InfrastructureCategoryRepositoryImpl implements DomainCategoryRepositoryPort {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<DomainCategoryEntity> findById(Long id) {
        try {
            return Optional.ofNullable(entityManager.find(DomainCategoryEntity.class, id));
        } catch (Exception e) {
            log.error("Error finding category by id: {}", id, e);
            throw new DataAccessException("Error finding category by id", e);
        }
    }

    @Override
    public List<DomainCategoryEntity> findAll() {
        try {
            TypedQuery<DomainCategoryEntity> query = entityManager.createQuery("SELECT c FROM DomainCategoryEntity c", DomainCategoryEntity.class);
            return query.getResultList();
        } catch (Exception e) {
            log.error("Error finding all categories", e);
            throw new DataAccessException("Error finding all categories", e);
        }
    }

    @Override
    public List<DomainCategoryEntity> findByParentId(Long parentId) {
        try {
            TypedQuery<DomainCategoryEntity> query = entityManager.createQuery("SELECT c FROM DomainCategoryEntity c WHERE c.parentId = :parentId", DomainCategoryEntity.class);
            query.setParameter("parentId", parentId);
            return query.getResultList();
        } catch (Exception e) {
            log.error("Error finding subcategories by parent id: {}", parentId, e);
            throw new DataAccessException("Error finding subcategories by parent id", e);
        }
    }
}

// Custom Exception Class
package ai.shreds.infrastructure;

public class DataAccessException extends RuntimeException {
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}