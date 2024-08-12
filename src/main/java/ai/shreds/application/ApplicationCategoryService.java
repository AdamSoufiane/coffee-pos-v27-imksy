package ai.shreds.application;

import ai.shreds.adapter.AdapterCategoryRequestParams;
import ai.shreds.adapter.AdapterCategoryResponseDTO;
import ai.shreds.adapter.AdapterCategoryMapper;
import ai.shreds.domain.DomainCategoryEntity;
import ai.shreds.domain.DomainCategoryRepositoryPort;
import ai.shreds.domain.DomainCategoryServicePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationCategoryService implements ApplicationCreateCategoryInputPort, ApplicationUpdateCategoryInputPort, ApplicationDeleteCategoryInputPort, ApplicationRetrieveCategoryHierarchyInputPort {
    private final DomainCategoryRepositoryPort domainCategoryRepositoryPort;
    private final DomainCategoryServicePort domainCategoryServicePort;
    private final AdapterCategoryMapper applicationCategoryMapper;
    private static final Logger logger = LoggerFactory.getLogger(ApplicationCategoryService.class);

    public ApplicationCategoryService(DomainCategoryRepositoryPort domainCategoryRepositoryPort, DomainCategoryServicePort domainCategoryServicePort, AdapterCategoryMapper applicationCategoryMapper) {
        this.domainCategoryRepositoryPort = domainCategoryRepositoryPort;
        this.domainCategoryServicePort = domainCategoryServicePort;
        this.applicationCategoryMapper = applicationCategoryMapper;
    }

    @Override
    public AdapterCategoryResponseDTO createCategory(AdapterCategoryRequestParams request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        logger.info("Creating category with name: {} and parent_id: {}", request.getName(), request.getParentId());
        DomainCategoryEntity categoryEntity = applicationCategoryMapper.toDomainEntity(request);
        domainCategoryServicePort.validateCategoryData(categoryEntity);
        DomainCategoryEntity savedEntity = domainCategoryRepositoryPort.save(categoryEntity);
        return applicationCategoryMapper.toAdapterDTO(savedEntity);
    }

    @Override
    public AdapterCategoryResponseDTO updateCategory(Long id, AdapterCategoryRequestParams request) {
        if (id == null || request == null) {
            throw new IllegalArgumentException("ID and request cannot be null");
        }
        logger.info("Updating category with id: {}", id);
        domainCategoryServicePort.checkCategoryExistence(id);
        DomainCategoryEntity categoryEntity = applicationCategoryMapper.toDomainEntity(request);
        categoryEntity.setId(id);
        domainCategoryServicePort.validateCategoryData(categoryEntity);
        DomainCategoryEntity updatedEntity = domainCategoryRepositoryPort.save(categoryEntity);
        return applicationCategoryMapper.toAdapterDTO(updatedEntity);
    }

    @Override
    public void deleteCategory(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        logger.info("Deleting category with id: {}", id);
        domainCategoryServicePort.checkCategoryExistence(id);
        domainCategoryRepositoryPort.deleteById(id);
    }

    @Override
    public List<AdapterCategoryResponseDTO> retrieveCategoryHierarchy(Long parent_id) {
        logger.info("Retrieving category hierarchy starting from parent_id: {}", parent_id);
        List<DomainCategoryEntity> categoryEntities = domainCategoryServicePort.buildCategoryHierarchy(parent_id);
        return categoryEntities.stream()
                .map(applicationCategoryMapper::toAdapterDTO)
                .collect(Collectors.toList());
    }
}