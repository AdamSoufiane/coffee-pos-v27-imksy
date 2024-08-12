package ai.shreds.application;

import ai.shreds.shared.SharedProductDTO;
import ai.shreds.shared.SharedRequestParams;

/**
 * Port interface for product service operations.
 */
public interface ApplicationProductServicePort {
    /**
     * Creates a new product based on the provided request parameters.
     *
     * @param request the product creation request parameters
     * @return the created product data transfer object
     */
    SharedProductDTO createProduct(SharedRequestParams request);
}