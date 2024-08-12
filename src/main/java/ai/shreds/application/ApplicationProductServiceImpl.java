package ai.shreds.application;

import ai.shreds.domain.DomainCreateProductServiceDomain;
import ai.shreds.domain.DomainProductRepositoryPort;
import ai.shreds.domain.DomainProductEntity;
import ai.shreds.shared.SharedProductDTO;
import ai.shreds.shared.SharedRequestParams;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class ApplicationProductServiceImpl implements ApplicationProductServicePort {
    private final DomainProductRepositoryPort productRepository;
    private final DomainCreateProductServiceDomain createProductServiceDomain;
    private static final Logger log = LoggerFactory.getLogger(ApplicationProductServiceImpl.class);

    @Override
    @Transactional
    public SharedProductDTO createProduct(SharedRequestParams request) {
        try {
            // Validate the input data
            createProductServiceDomain.validateProductData(request);

            // Process the product creation
            SharedProductDTO productDTO = createProductServiceDomain.processProductCreation(request);

            // Save the product entity to the repository
            productRepository.save(mapToEntity(productDTO));

            return productDTO;
        } catch (Exception e) {
            log.error("Error creating product: ", e);
            throw new RuntimeException("Failed to create product", e);
        }
    }

    private DomainProductEntity mapToEntity(SharedProductDTO productDTO) {
        return new DomainProductEntity(
                productDTO.getId(),
                productDTO.getName(),
                productDTO.getDescription(),
                productDTO.getPrice(),
                productDTO.getCategoryId(),
                productDTO.getStockQuantity()
        );
    }
}