package ai.shreds.domain;

import java.util.List;

/**
 * Interface for Category Repository operations.
 * Provides methods to perform CRUD operations and retrieve hierarchical data.
 */
public interface DomainCategoryRepositoryPort {

    /**
     * Saves a new or updated category entity to the database.
     * 
     * @param category the category entity to save
     * @return the saved category entity
     */
    DomainCategoryEntity save(DomainCategoryEntity category);

    /**
     * Finds a category by its unique identifier.
     * 
     * @param id the unique identifier of the category
     * @return the found category entity
     */
    DomainCategoryEntity findById(Long id);

    /**
     * Deletes a category by its unique identifier.
     * 
     * @param id the unique identifier of the category
     */
    void deleteById(Long id);

    /**
     * Finds all categories that have the specified parent_id.
     * 
     * @param parentId the parent identifier of the categories
     * @return a list of categories with the specified parent_id
     */
    List<DomainCategoryEntity> findAllByParentId(Long parentId);

    /**
     * Finds all categories in the database.
     * 
     * @return a list of all categories
     */
    List<DomainCategoryEntity> findAll();
}

/**
 * Implementation Note:
 * Use Lombok annotations for getters and setters in DomainCategoryEntity.
 */