package ai.shreds.domain;

import ai.shreds.shared.SharedCategoryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DomainCategoryService implements DomainCategoryServicePort {
    private final DomainCategoryRepositoryPort categoryRepository;
    private static final Logger logger = LoggerFactory.getLogger(DomainCategoryService.class);

    public DomainCategoryService(DomainCategoryRepositoryPort categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void validateCategoryData(DomainCategoryEntity category) {
        logger.info("Validating category data: {}", category);
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
        if (category.getParentId() != null) {
            DomainCategoryEntity parentCategory = categoryRepository.findById(category.getParentId());
            if (parentCategory == null) {
                throw new IllegalArgumentException("Parent category does not exist");
            }
        }
    }

    @Override
    public boolean checkCategoryExistence(Long id) {
        logger.info("Checking existence of category with id: {}", id);
        return categoryRepository.findById(id) != null;
    }

    @Override
    public List<DomainCategoryEntity> buildCategoryHierarchy(Long parentId) {
        logger.info("Building category hierarchy starting from parentId: {}", parentId);
        List<DomainCategoryEntity> allCategories = categoryRepository.findAll();
        List<DomainCategoryEntity> hierarchy = new ArrayList<>();
        buildHierarchyRecursive(hierarchy, allCategories, parentId);
        return hierarchy;
    }

    private void buildHierarchyRecursive(List<DomainCategoryEntity> hierarchy, List<DomainCategoryEntity> allCategories, Long parentId) {
        for (DomainCategoryEntity category : allCategories) {
            if ((parentId == null && category.getParentId() == null) || (parentId != null && parentId.equals(category.getParentId()))) {
                hierarchy.add(category);
                buildHierarchyRecursive(hierarchy, allCategories, category.getId());
            }
        }
    }
}