package ai.shreds.domain;

import java.util.List;
import java.util.Optional;

/**
 * DomainCategoryServicePort is an interface that defines the contract for category retrieval operations.
 */
public interface DomainCategoryServicePort {
    /**
     * Fetches detailed information about a specific category by its id.
     *
     * @param id the unique identifier of the category
     * @return the DomainCategoryEntity object containing category details
     * @throws CategoryNotFoundException if the category is not found
     */
    DomainCategoryEntity getCategoryDetails(Long id) throws CategoryNotFoundException;

    /**
     * Fetches a list of all categories.
     *
     * @return a list of DomainCategoryEntity objects
     */
    List<DomainCategoryEntity> getAllCategories();

    /**
     * Fetches a list of subcategories for a given parent category id.
     *
     * @param parentId the unique identifier of the parent category
     * @return a list of DomainCategoryEntity objects
     * @throws ParentCategoryNotFoundException if the parent category is not found
     */
    List<DomainCategoryEntity> getSubcategories(Long parentId) throws ParentCategoryNotFoundException;
}