package ai.shreds.application;

import ai.shreds.adapter.AdapterCategoryRequestParams;
import ai.shreds.adapter.AdapterCategoryResponseDTO;

/**
 * Interface for creating a new category in the hierarchy.
 */
public interface ApplicationCreateCategoryInputPort {
    /**
     * Creates a new category based on the provided request parameters.
     *
     * @param request the request parameters for creating a new category
     * @return the response DTO containing the created category details
     */
    AdapterCategoryResponseDTO createCategory(AdapterCategoryRequestParams request);
}