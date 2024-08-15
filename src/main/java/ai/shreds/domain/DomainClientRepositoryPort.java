package ai.shreds.domain;

import java.util.UUID;

/**
 * DomainClientRepositoryPort is an interface for interacting with the Client entity at the domain level.
 * It provides methods to save a client entity to the database and retrieve a client entity by its unique identifier (UUID).
 */
public interface DomainClientRepositoryPort {
    /**
     * Persists the given client entity to the database.
     *
     * @param client the client entity to be saved
     */
    void save(DomainClientEntity client);

    /**
     * Retrieves a client entity by its unique identifier (UUID).
     *
     * @param id the unique identifier of the client entity
     * @return the client entity
     */
    DomainClientEntity findById(UUID id);
}