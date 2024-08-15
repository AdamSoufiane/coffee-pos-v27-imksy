package ai.shreds.domain;

import java.util.List;

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
     */
    DomainSupplierEntity findById(Long id);

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
     */
    void deleteById(Long id);
}