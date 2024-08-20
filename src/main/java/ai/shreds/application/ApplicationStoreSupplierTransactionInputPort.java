package ai.shreds.application;

import ai.shreds.adapter.AdapterRequestParams;
import ai.shreds.adapter.AdapterResponseDTO;

/**
 * This interface defines the contract for storing supplier transactions.
 * Implementations of this interface should handle the business logic for
 * storing supplier transactions and returning a confirmation response.
 */
@FunctionalInterface
public interface ApplicationStoreSupplierTransactionInputPort {
    /**
     * Stores supplier transaction details, calculates the total amount, stores the transaction in the database,
     * and returns a confirmation response.
     *
     * @param params the request parameters containing supplier transaction details
     * @return the response DTO containing the stored transaction details
     */
    AdapterResponseDTO storeSupplierTransaction(AdapterRequestParams params);
}