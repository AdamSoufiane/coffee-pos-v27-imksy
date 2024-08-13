package ai.shreds.application;

import ai.shreds.adapter.AdapterCreateProductRequest;
import ai.shreds.adapter.AdapterProductResponse;

/**
 * ApplicationCreateProductInputPort defines a contract for creating a product in the application layer.
 */
public interface ApplicationCreateProductInputPort {
    /**
     * Creates a new product with the provided details.
     *
     * @param request the product creation request containing product details
     * @return the response containing created product details
     */
    AdapterProductResponse createProduct(AdapterCreateProductRequest request);
}