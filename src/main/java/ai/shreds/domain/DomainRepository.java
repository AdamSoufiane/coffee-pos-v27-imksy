package ai.shreds.domain;

import java.util.UUID;

/**
 * DomainRepository defines the contract for data access operations related to SupplierTransaction.
 */
public interface DomainRepository {
    /**
     * Persists a SupplierTransaction entity to the database.
     *
     * @param supplierTransaction the SupplierTransaction entity to be saved
     */
    void save(DomainSupplierTransaction supplierTransaction);

    /**
     * Finds a SupplierTransaction entity by its unique identifier.
     *
     * @param id the unique identifier of the SupplierTransaction
     * @return the found SupplierTransaction entity
     */
    DomainSupplierTransaction findById(UUID id);
}