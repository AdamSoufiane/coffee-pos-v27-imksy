package ai.shreds.application;

import ai.shreds.shared.AdapterProductResponseDTO;

/**
 * Interface for notifying other services about product updates.
 */
public interface ApplicationUpdateProductOutputPort {
    /**
     * Notifies other services about product updates by producing a message to a Kafka topic.
     *
     * @param response the response DTO containing updated product information
     */
    void notifyProductUpdate(AdapterProductResponseDTO response);
}