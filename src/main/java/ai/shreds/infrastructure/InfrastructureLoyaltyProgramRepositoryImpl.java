package ai.shreds.infrastructure;

import ai.shreds.domain.DomainLoyaltyProgramEntity;
import ai.shreds.domain.DomainLoyaltyProgramRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Repository
public class InfrastructureLoyaltyProgramRepositoryImpl implements DomainLoyaltyProgramRepositoryPort {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(InfrastructureLoyaltyProgramRepositoryImpl.class);

    /**
     * Saves the loyalty program entity to the database.
     * 
     * @param loyaltyProgram the loyalty program entity to save
     */
    @Override
    @Transactional
    public void save(DomainLoyaltyProgramEntity loyaltyProgram) {
        try {
            logger.info("Saving loyalty program: {}", loyaltyProgram);
            entityManager.persist(loyaltyProgram);
        } catch (Exception e) {
            logger.error("Error saving loyalty program: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to save loyalty program", e);
        }
    }

    /**
     * Finds a loyalty program entity by its ID.
     * 
     * @param id the UUID of the loyalty program entity
     * @return the found loyalty program entity
     */
    @Override
    public DomainLoyaltyProgramEntity findById(UUID id) {
        try {
            logger.info("Finding loyalty program by ID: {}", id);
            return entityManager.find(DomainLoyaltyProgramEntity.class, id);
        } catch (Exception e) {
            logger.error("Error finding loyalty program by ID: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to find loyalty program", e);
        }
    }
}