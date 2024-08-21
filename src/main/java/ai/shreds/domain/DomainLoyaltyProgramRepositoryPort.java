package ai.shreds.domain;

import java.util.UUID;

/**
 * Interface for CRUD operations on LoyaltyProgram entities.
 */
public interface DomainLoyaltyProgramRepositoryPort {
    /**
     * Saves a loyalty program entity to the database.
     *
     * @param loyaltyProgram the loyalty program entity to save
     */
    void save(DomainLoyaltyProgramEntity loyaltyProgram);

    /**
     * Finds a loyalty program entity by its unique identifier.
     *
     * @param id the unique identifier of the loyalty program
     * @return the found loyalty program entity
     */
    DomainLoyaltyProgramEntity findById(UUID id);
}