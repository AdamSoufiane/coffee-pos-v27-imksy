package ai.shreds.domain;

import java.util.List;
import ai.shreds.domain.DomainSupplierEntity;
import ai.shreds.domain.EntityNotFoundException;

/**
 * DomainSupplierRepositoryPort is an interface that defines the contract for CRUD operations on Supplier entities.
 */
public interface DomainSupplierRepositoryPort {
    /**
     * Saves a supplier entity to the database.
     *
     * @param supplier the supplier entity to be saved
     */
    void save(DomainSupplierEntity supplier);

    /**
     * Finds a supplier entity by its ID.
     *
     * @param id the ID of the supplier entity to be found
     * @return the found supplier entity
     * @throws EntityNotFoundException if no supplier entity is found with the given ID
     */
    DomainSupplierEntity findById(Long id) throws EntityNotFoundException;

    /**
     * Finds all supplier entities in the database.
     *
     * @return a list of all supplier entities
     */
    List<DomainSupplierEntity> findAll();

    /**
     * Deletes a supplier entity by its ID.
     *
     * @param id the ID of the supplier entity to be deleted
     * @throws EntityNotFoundException if no supplier entity is found with the given ID
     */
    void deleteById(Long id) throws EntityNotFoundException;
}