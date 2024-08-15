package ai.shreds.application;

import ai.shreds.shared.ApplicationUpdateSupplierDTO;
import ai.shreds.shared.ApplicationSupplierDTO;

/**
 * Interface for updating an existing supplier record in the database.
 * This interface defines the contract for updating supplier records
 * and ensures that the implementation adheres to the business rules
 * and logic defined in the application layer.
 */
public interface ApplicationUpdateSupplierInputPort {
    /**
     * Updates an existing supplier record in the database.
     * This method is responsible for handling the update operation
     * by accepting the supplier ID and the data transfer object containing
     * the updated supplier information.
     *
     * @param supplierId the ID of the supplier to update
     * @param updateDto  the data transfer object containing the updated supplier information
     * @return the updated supplier data transfer object
     */
    ApplicationSupplierDTO updateSupplier(Long supplierId, ApplicationUpdateSupplierDTO updateDto);
}