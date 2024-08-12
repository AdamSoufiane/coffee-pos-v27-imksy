package ai.shreds.application;

import ai.shreds.shared.SharedCategoryDTO;
import java.util.List;

/**
 * ApplicationCategoryServicePort defines the contract for category retrieval operations.
 */
public interface ApplicationCategoryServicePort {
    /**
     * Fetches detailed information about a specific category by its id.
     * @param id the unique identifier of the category
     * @return SharedCategoryDTO containing the category details
     */
    SharedCategoryDTO getCategoryDetails(Long id);

    /**
     * Fetches a list of all categories.
     * @return List of SharedCategoryDTO containing all categories
     */
    List<SharedCategoryDTO> getAllCategories();

    /**
     * Fetches a list of subcategories for a given parent category id.
     * @param parentId the unique identifier of the parent category
     * @return List of SharedCategoryDTO containing the subcategories
     */
    List<SharedCategoryDTO> getSubcategories(Long parentId);
}