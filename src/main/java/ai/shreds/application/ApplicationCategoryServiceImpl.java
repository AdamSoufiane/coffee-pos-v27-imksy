package ai.shreds.application;

import ai.shreds.domain.DomainCategoryServicePort;
import ai.shreds.shared.SharedCategoryDTO;
import ai.shreds.shared.SharedCategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.dao.DataAccessException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class ApplicationCategoryServiceImpl implements ApplicationCategoryServicePort {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationCategoryServiceImpl.class);

    private final DomainCategoryServicePort domainService;
    private final SharedCategoryMapper sharedCategoryMapper;

    public ApplicationCategoryServiceImpl(DomainCategoryServicePort domainService, SharedCategoryMapper sharedCategoryMapper) {
        this.domainService = domainService;
        this.sharedCategoryMapper = sharedCategoryMapper;
    }

    @Override
    public SharedCategoryDTO getCategoryDetails(Long id) {
        try {
            return sharedCategoryMapper.toSharedCategoryDTO(domainService.getCategoryDetails(id));
        } catch (DataAccessException e) {
            logger.error("Error fetching category details for id: {}", id, e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category with ID " + id + " not found", e);
        }
    }

    @Override
    public List<SharedCategoryDTO> getAllCategories() {
        try {
            return domainService.getAllCategories().stream()
                    .map(sharedCategoryMapper::toSharedCategoryDTO)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            logger.error("Error fetching all categories", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occurred while fetching all categories", e);
        }
    }

    @Override
    public List<SharedCategoryDTO> getSubcategories(Long parentId) {
        try {
            return domainService.getSubcategories(parentId).stream()
                    .map(sharedCategoryMapper::toSharedCategoryDTO)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            logger.error("Error fetching subcategories for parent id: {}", parentId, e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Subcategories for parent ID " + parentId + " not found", e);
        }
    }
 
    // Note: Use Lombok annotations for getters, setters, and constructors in the class.
}