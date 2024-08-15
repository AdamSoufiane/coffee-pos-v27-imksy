package ai.shreds.application;

import ai.shreds.application.ApplicationSupplierDTO;
import shared.ApplicationCreateSupplierDTO;

/**
 * Interface for creating a new supplier record.
 */
public interface ApplicationCreateSupplierInputPort {
    /**
     * Creates a new supplier.
     *
     * @param dto the supplier data transfer object
     * @return the created supplier data transfer object
     */
    ApplicationSupplierDTO createSupplier(ApplicationCreateSupplierDTO dto);
}