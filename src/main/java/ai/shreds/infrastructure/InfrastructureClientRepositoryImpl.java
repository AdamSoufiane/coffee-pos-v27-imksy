package ai.shreds.infrastructure;

import ai.shreds.domain.DomainClientEntity;
import ai.shreds.domain.DomainClientRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class InfrastructureClientRepositoryImpl implements DomainClientRepositoryPort {

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureClientRepositoryImpl.class);
    private final ClientJpaRepository repository;
    private final InfrastructureClientEntityMapper infrastructureClientEntityMapper;

    @Autowired
    public InfrastructureClientRepositoryImpl(ClientJpaRepository repository, InfrastructureClientEntityMapper infrastructureClientEntityMapper) {
        this.repository = repository;
        this.infrastructureClientEntityMapper = infrastructureClientEntityMapper;
    }

    @Override
    @Transactional
    public void save(DomainClientEntity client) {
        try {
            InfrastructureClientEntity entity = infrastructureClientEntityMapper.toInfrastructure(client);
            repository.save(entity);
        } catch (Exception e) {
            logger.error("Error saving client entity", e);
            throw e;
        }
    }

    @Override
    public DomainClientEntity findById(UUID id) {
        return repository.findById(id)
                .map(infrastructureClientEntityMapper::toDomain)
                .orElse(null);
    }
}