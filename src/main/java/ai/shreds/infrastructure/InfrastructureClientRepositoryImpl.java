package ai.shreds.infrastructure;

import ai.shreds.domain.DomainClientEntity;
import ai.shreds.domain.DomainClientRepositoryPort;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.util.logging.Logger;

@Repository
@RequiredArgsConstructor
public class InfrastructureClientRepositoryImpl implements DomainClientRepositoryPort {

    private static final Logger LOGGER = Logger.getLogger(InfrastructureClientRepositoryImpl.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(@NotNull DomainClientEntity client) {
        LOGGER.info("Saving client with ID: " + client.getClientId());
        entityManager.persist(client);
    }

    @Override
    public DomainClientEntity findById(@NotNull UUID id) {
        LOGGER.info("Finding client with ID: " + id);
        return entityManager.find(DomainClientEntity.class, id);
    }
}