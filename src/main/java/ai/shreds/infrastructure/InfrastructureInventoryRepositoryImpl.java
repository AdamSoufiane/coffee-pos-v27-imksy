package ai.shreds.infrastructure;

import ai.shreds.domain.DomainInventoryEntity;
import ai.shreds.domain.DomainInventoryRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Repository
public class InfrastructureInventoryRepositoryImpl implements DomainInventoryRepositoryPort {

    private static final Logger log = LoggerFactory.getLogger(InfrastructureInventoryRepositoryImpl.class);

    @PersistenceContext
    @Getter
    @Setter
    private EntityManager entityManager;

    @Override
    public DomainInventoryEntity findByProductId(UUID productId) {
        try {
            TypedQuery<DomainInventoryEntity> query = entityManager.createQuery(
                "SELECT i FROM DomainInventoryEntity i WHERE i.productId = :productId", DomainInventoryEntity.class);
            return query.setParameter("productId", productId).getSingleResult();
        } catch (Exception e) {
            log.error("Error finding inventory by product ID", e);
            throw new RuntimeException("Error finding inventory by product ID", e);
        }
    }

    @Override
    @Transactional
    public void save(DomainInventoryEntity inventory) {
        try {
            if (inventory.getProductId() == null) {
                entityManager.persist(inventory);
            } else {
                entityManager.merge(inventory);
            }
        } catch (Exception e) {
            log.error("Error saving inventory", e);
            throw new RuntimeException("Error saving inventory", e);
        }
    }
}