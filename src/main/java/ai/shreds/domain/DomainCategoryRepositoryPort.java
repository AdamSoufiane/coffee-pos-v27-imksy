package ai.shreds.domain;

import java.util.List;
import java.util.Optional;

/**
 * DomainCategoryRepositoryPort is an interface for category data access operations.
 * It provides methods to find categories by ID, retrieve all categories, and find subcategories by parent ID.
 */
public interface DomainCategoryRepositoryPort {
    /**
     * Finds a category by its ID.
     * @param id the unique identifier of the category
     * @return an Optional containing the DomainCategoryEntity if found, otherwise an empty Optional
     */
    Optional<DomainCategoryEntity> findById(Long id);

    /**
     * Retrieves all categories.
     * @return a list of all DomainCategoryEntity objects
     */
    List<DomainCategoryEntity> findAll();

    /**
     * Finds subcategories by the parent category ID.
     * @param parentId the unique identifier of the parent category
     * @return a list of DomainCategoryEntity objects representing the subcategories
     */
    List<DomainCategoryEntity> findByParentId(Long parentId);
}