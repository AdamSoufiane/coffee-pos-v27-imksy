package ai.shreds.domain;

import java.util.UUID;

/**
 * DomainSaveSupplierTransactionPort is an interface that defines the contract for saving a SupplierTransaction entity.
 * This interface will be implemented by a class in the infrastructure layer that interacts with the actual database.
 */
public interface DomainSaveSupplierTransactionPort {

    /**
     * Saves a SupplierTransaction entity to the database.
     * @param supplierTransaction the SupplierTransaction entity to be saved.
     */
    void saveSupplierTransaction(DomainSupplierTransaction supplierTransaction);
}