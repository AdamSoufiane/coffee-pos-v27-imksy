package ai.shreds.domain;

import ai.shreds.shared.SharedCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the DomainCategoryServicePort interface.
 * This class handles the business logic for category retrieval operations.
 */
@Service
@RequiredArgsConstructor
public class DomainCategoryServiceImpl implements DomainCategoryServicePort {

    private static final Logger logger = LoggerFactory.getLogger(DomainCategoryServiceImpl.class);

    private final DomainCategoryRepositoryPort categoryRepository;
    private final SharedCategoryMapper categoryMapper;

    /**
     * Fetches detailed information about a specific category by its id.
     * @param id the unique identifier of the category
     * @return the category entity
     * @throws CategoryNotFoundException if the category is not found
     */
    @Override
    public DomainCategoryEntity getCategoryDetails(Long id) {
        logger.info("Entering getCategoryDetails with id: {}", id);
        validateId(id);
        Optional<DomainCategoryEntity> categoryOpt = categoryRepository.findById(id);
        if (categoryOpt.isEmpty()) {
            logger.error("Category not found with id: {}", id);
            throw new CategoryNotFoundException("Category not found with id: " + id);
        }
        logger.info("Exiting getCategoryDetails with result: {}", categoryOpt.get());
        return categoryOpt.get();
    }

    /**
     * Fetches a list of all categories.
     * @return a list of category entities
     */
    @Override
    public List<DomainCategoryEntity> getAllCategories() {
        logger.info("Entering getAllCategories");
        List<DomainCategoryEntity> categories = categoryRepository.findAll();
        logger.info("Exiting getAllCategories with result: {}", categories);
        return categories;
    }

    /**
     * Fetches a list of subcategories for a given parent category id.
     * @param parentId the unique identifier of the parent category
     * @return a list of subcategory entities
     * @throws CategoryNotFoundException if the parent category is not found
     */
    @Override
    public List<DomainCategoryEntity> getSubcategories(Long parentId) {
        logger.info("Entering getSubcategories with parentId: {}", parentId);
        validateId(parentId);
        Optional<DomainCategoryEntity> parentCategoryOpt = categoryRepository.findById(parentId);
        if (parentCategoryOpt.isEmpty()) {
            logger.error("Parent category not found with id: {}", parentId);
            throw new CategoryNotFoundException("Parent category not found with id: " + parentId);
        }
        List<DomainCategoryEntity> subcategories = categoryRepository.findByParentId(parentId);
        logger.info("Exiting getSubcategories with result: {}", subcategories);
        return subcategories;
    }

    /**
     * Validates the input id parameter.
     * @param id the id to validate
     * @throws IllegalArgumentException if the id is null or less than 1
     */
    private void validateId(Long id) {
        if (id == null || id < 1) {
            logger.error("Invalid id: {}", id);
            throw new IllegalArgumentException("Id must be a positive number");
        }
    }
}