package ai.shreds.domain;

import java.util.List;

/**
 * Service interface for managing categories within the domain.
 */
public interface DomainCategoryServicePort {
    /**
     * Validates the data for creating or updating a category.
     * Ensures the category name is not null or empty.
     * Checks that the category name is unique within the same parent category.
     *
     * @param category the category entity to validate
     */
    void validateCategoryData(DomainCategoryEntity category);

    /**
     * Checks if a category exists given its identifier.
     *
     * @param id the identifier of the category
     * @return true if the category exists, false otherwise
     */
    boolean checkCategoryExistence(Long id);

    /**
     * Constructs the hierarchical structure of categories starting from a given parent_id.
     *
     * @param parentId the identifier of the parent category
     * @return a list of category entities forming the hierarchy
     */
    List<DomainCategoryEntity> buildCategoryHierarchy(Long parentId);
}