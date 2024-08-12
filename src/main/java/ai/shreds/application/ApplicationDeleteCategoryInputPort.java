package ai.shreds.application;

/**
 * Interface for deleting a category in the application layer.
 */
public interface ApplicationDeleteCategoryInputPort {
    /**
     * Deletes a category given its unique identifier.
     *
     * @param id the unique identifier of the category to be deleted
     */
    void deleteCategory(Long id);
}