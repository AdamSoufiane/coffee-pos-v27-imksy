package ai.shreds.adapter;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AdapterCreateProductResponse encapsulates the response data for the create product API endpoint.
 * It includes the status of the operation, the unique identifier of the newly created product,
 * and a message providing additional information about the result.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class AdapterCreateProductResponse {

    /**
     * Status of the product creation operation (e.g., success or failure).
     */
    private String status;

    /**
     * Unique identifier of the newly created product.
     */
    private UUID product_id;

    /**
     * Additional information about the result of the operation.
     */
    private String message;
}