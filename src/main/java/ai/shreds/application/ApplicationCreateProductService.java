package ai.shreds.application;

import ai.shreds.domain.DomainCreateProductServiceDomain;
import ai.shreds.shared.SharedProductDTO;
import ai.shreds.shared.SharedRequestParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * Service implementation for creating products.
 */
@Service
public class ApplicationCreateProductService implements ApplicationCreateProductInputPort {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationCreateProductService.class);
    private final DomainCreateProductServiceDomain domainService;

    /**
     * Constructor for dependency injection.
     *
     * @param domainService the domain service for product creation
     */
    @Autowired
    public ApplicationCreateProductService(DomainCreateProductServiceDomain domainService) {
        this.domainService = domainService;
    }

    /**
     * Creates a new product.
     *
     * @param request the request parameters for creating a product
     * @return the created product DTO
     */
    @Override
    public SharedProductDTO createProduct(SharedRequestParams request) {
        try {
            logger.info("Starting product creation process");
            // Validate the product data
            domainService.validateProductData(request);

            // Process the product creation
            SharedProductDTO productDTO = domainService.processProductCreation(request);
            logger.info("Product creation successful: {}", productDTO.getId());
            return productDTO;
        } catch (Exception e) {
            logger.error("Error occurred during product creation", e);
            throw new RuntimeException("Product creation failed", e);
        }
    }
}