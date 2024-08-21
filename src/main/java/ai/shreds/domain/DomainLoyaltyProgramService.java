package ai.shreds.domain;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DomainLoyaltyProgramService implements DomainClientRepositoryPort, DomainLoyaltyProgramRepositoryPort {

    private static final Logger logger = LoggerFactory.getLogger(DomainLoyaltyProgramService.class);

    private final DomainClientRepositoryPort clientRepository;
    private final DomainLoyaltyProgramRepositoryPort loyaltyProgramRepository;

    /**
     * Saves a client entity to the database.
     * @param client The client entity to be saved.
     */
    @Override
    @Transactional
    public void save(DomainClientEntity client) {
        if (client == null) {
            throw new DomainValidationException("Client entity cannot be null");
        }
        try {
            clientRepository.save(client);
            logger.info("Client entity saved successfully, clientId: {}", client.getClientId());
        } catch (Exception e) {
            logger.error("Error saving client entity: {}", e.getMessage());
            throw new DomainValidationException("Error saving client entity: " + e.getMessage());
        }
    }

    /**
     * Saves a loyalty program entity to the database.
     * @param loyaltyProgram The loyalty program entity to be saved.
     */
    @Override
    @Transactional
    public void save(DomainLoyaltyProgramEntity loyaltyProgram) {
        if (loyaltyProgram == null) {
            throw new DomainValidationException("Loyalty program entity cannot be null");
        }
        if (loyaltyProgram.getEnrollmentDate().isAfter(LocalDateTime.now())) {
            throw new DomainValidationException("Enrollment date cannot be in the future");
        }
        try {
            loyaltyProgramRepository.save(loyaltyProgram);
            logger.info("Loyalty program entity saved successfully, loyaltyProgramId: {}", loyaltyProgram.getLoyaltyProgramId());
        } catch (Exception e) {
            logger.error("Error saving loyalty program entity: {}", e.getMessage());
            throw new DomainValidationException("Error saving loyalty program entity: " + e.getMessage());
        }
    }

    /**
     * Finds a client entity by its ID.
     * @param id The UUID of the client entity.
     * @return The found client entity.
     */
    @Override
    public DomainClientEntity findClientById(UUID id) {
        try {
            DomainClientEntity client = clientRepository.findById(id);
            logger.info("Client entity found successfully, clientId: {}", id);
            return client;
        } catch (Exception e) {
            logger.error("Error finding client entity by ID: {}", e.getMessage());
            throw new DomainValidationException("Error finding client entity by ID: " + e.getMessage());
        }
    }

    /**
     * Finds a loyalty program entity by its ID.
     * @param id The UUID of the loyalty program entity.
     * @return The found loyalty program entity.
     */
    @Override
    public DomainLoyaltyProgramEntity findLoyaltyProgramById(UUID id) {
        try {
            DomainLoyaltyProgramEntity loyaltyProgram = loyaltyProgramRepository.findById(id);
            logger.info("Loyalty program entity found successfully, loyaltyProgramId: {}", id);
            return loyaltyProgram;
        } catch (Exception e) {
            logger.error("Error finding loyalty program entity by ID: {}", e.getMessage());
            throw new DomainValidationException("Error finding loyalty program entity by ID: " + e.getMessage());
        }
    }
}