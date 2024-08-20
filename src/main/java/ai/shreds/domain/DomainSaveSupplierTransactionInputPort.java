package ai.shreds.domain;

import ai.shreds.domain.DomainSupplierTransaction;

/**
 * Interface for saving supplier transactions.
 * This interface will be implemented by classes in the infrastructure layer to provide the actual saving functionality.
 */
public interface DomainSaveSupplierTransactionInputPort {
    void save(DomainSupplierTransaction supplierTransaction);
}