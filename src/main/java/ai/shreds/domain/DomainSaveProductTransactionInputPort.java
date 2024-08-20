package ai.shreds.domain;

import ai.shreds.domain.DomainProductTransaction;

/**
 * Interface for saving a product transaction.
 */
public interface DomainSaveProductTransactionInputPort {
    /**
     * Saves a product transaction to the database.
     *
     * @param productTransaction the product transaction to save
     */
    void save(DomainProductTransaction productTransaction);
}