package ai.shreds.application;

import shared.ApplicationSupplierDTO;

/**
 * Interface for retrieving supplier details by their unique identifier.
 */
public interface ApplicationGetSupplierByIdInputPort {
    /**
     * Retrieves a supplier record by its ID.
     *
     * @param id the unique identifier of the supplier
     * @return the supplier details as ApplicationSupplierDTO
     */
    ApplicationSupplierDTO getSupplierById(Long id);
}

// Note: Use Lombok annotations if applicable