package ai.shreds.domain;

import ai.shreds.infrastructure.InfrastructureKafkaProducer;
import ai.shreds.shared.AdapterProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Service
public class DomainUpdateProductService implements DomainProductUpdatePort {

    private final DomainProductRepositoryPort productRepository;
    private final DomainInventoryRepositoryPort inventoryRepository;
    private final InfrastructureKafkaProducer kafkaProducer;

    @Autowired
    public DomainUpdateProductService(DomainProductRepositoryPort productRepository, DomainInventoryRepositoryPort inventoryRepository, InfrastructureKafkaProducer kafkaProducer) {
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    @Transactional
    public DomainProductEntity updateProduct(DomainProductEntity product) {
        // Validate product data
        validateProductData(product);

        // Update product details in the database
        DomainProductEntity updatedProduct = productRepository.save(product);

        // Ensure inventory information is consistent
        DomainInventoryEntity inventory = inventoryRepository.findByProductId(updatedProduct.getId());
        if (inventory == null) {
            inventory = new DomainInventoryEntity();
            inventory.setProduct_id(updatedProduct.getId());
        }
        inventory.setAvailable_quantity(product.getStock_quantity()); // Ensure inventory consistency
        inventoryRepository.save(inventory);

        // Notify other services about the product update
        notifyProductUpdate(updatedProduct);

        return updatedProduct;
    }

    private void validateProductData(DomainProductEntity product) {
        if (product.getId() == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        if (product.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price must be positive");
        }
        if (product.getStock_quantity() < 0) {
            throw new IllegalArgumentException("Stock quantity must be non-negative");
        }
        // Additional validation rules can be added here
    }

    // Add proper exception handling for database operations
    @Transactional(rollbackFor = Exception.class)
    public void updateProductWithRollback(DomainProductEntity product) {
        try {
            updateProduct(product);
        } catch (Exception e) {
            // Handle exception and rollback transaction
            throw new RuntimeException("Failed to update product", e);
        }
    }

    private void notifyProductUpdate(DomainProductEntity product) {
        // Logic to produce a message to the ProductUpdates Kafka topic
        kafkaProducer.produceUpdateMessage(product);
    }
}