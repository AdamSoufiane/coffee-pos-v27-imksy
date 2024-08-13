package ai.shreds.adapter;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * AdapterProductResponse is a data transfer object (DTO) that represents the response structure for product-related API operations.
 * It includes fields such as id, name, description, price, categoryId, stockQuantity, createdAt, updatedAt, and message.
 */
@Data
@Builder
public class AdapterProductResponse {
    /**
     * Unique identifier for the product.
     */
    private UUID id;

    /**
     * Name of the product.
     */
    private String name;

    /**
     * Description of the product.
     */
    private String description;

    /**
     * Price of the product.
     */
    private BigDecimal price;

    /**
     * Identifier for the category the product belongs to.
     */
    private UUID categoryId;

    /**
     * Quantity of the product in stock.
     */
    private Integer stockQuantity;

    /**
     * Timestamp when the product was created.
     */
    private Timestamp createdAt;

    /**
     * Timestamp when the product was last updated.
     */
    private Timestamp updatedAt;

    /**
     * Message related to the response.
     */
    private String message;

    /**
     * Sets the message for the response.
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}