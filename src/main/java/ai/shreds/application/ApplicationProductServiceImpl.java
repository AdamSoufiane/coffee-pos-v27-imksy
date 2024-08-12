package ai.shreds.application;

import ai.shreds.domain.DomainCreateProductServiceDomain;
import ai.shreds.domain.DomainProductRepositoryPort;
import ai.shreds.shared.SharedProductDTO;
import ai.shreds.shared.SharedRequestParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class ApplicationProductServiceImpl implements ApplicationProductServicePort {
    private final DomainProductRepositoryPort productRepository;
    private final DomainCreateProductServiceDomain createProductServiceDomain;

    @Override
    @Transactional
    public SharedProductDTO createProduct(SharedRequestParams request) {
        try {
            // Validate the input data
            createProductServiceDomain.validateProductData(request);

            // Process the product creation
            SharedProductDTO productDTO = createProductServiceDomain.processProductCreation(request);

            // Save the product entity to the repository
            productRepository.save(productDTO.toEntity());

            return productDTO;
        } catch (Exception e) {
            log.error("Error creating product: ", e);
            throw new RuntimeException("Failed to create product", e);
        }
    }
}