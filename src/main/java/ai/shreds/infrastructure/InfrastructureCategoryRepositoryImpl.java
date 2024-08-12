package ai.shreds.infrastructure;

import ai.shreds.domain.DomainCategoryEntity;
import ai.shreds.domain.DomainCategoryRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class InfrastructureCategoryRepositoryImpl implements DomainCategoryRepositoryPort {

    private static final Logger log = LoggerFactory.getLogger(InfrastructureCategoryRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<DomainCategoryEntity> findById(Long id) {
        try {
            return Optional.ofNullable(entityManager.find(DomainCategoryEntity.class, id));
        } catch (PersistenceException e) {
            log.error("Error finding category by id: {}", id, e);
            throw new DataAccessException("DB_ERROR", "Error finding category by id", e);
        }
    }

    @Override
    public List<DomainCategoryEntity> findAll() {
        try {
            TypedQuery<DomainCategoryEntity> query = entityManager.createQuery("SELECT c FROM DomainCategoryEntity c", DomainCategoryEntity.class);
            return query.getResultList();
        } catch (PersistenceException e) {
            log.error("Error finding all categories", e);
            throw new DataAccessException("DB_ERROR", "Error finding all categories", e);
        }
    }

    @Override
    public List<DomainCategoryEntity> findByParentId(Long parentId) {
        try {
            TypedQuery<DomainCategoryEntity> query = entityManager.createQuery("SELECT c FROM DomainCategoryEntity c WHERE c.parentId = :parentId", DomainCategoryEntity.class);
            query.setParameter("parentId", parentId);
            return query.getResultList();
        } catch (PersistenceException e) {
            log.error("Error finding subcategories by parent id: {}", parentId, e);
            throw new DataAccessException("DB_ERROR", "Error finding subcategories by parent id", e);
        }
    }
}