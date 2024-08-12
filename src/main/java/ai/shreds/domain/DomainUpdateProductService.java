package ai.shreds.domain;

import ai.shreds.infrastructure.InfrastructureKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        validateProductData(product);
        DomainProductEntity updatedProduct = productRepository.save(product);
        DomainInventoryEntity inventory = inventoryRepository.findByProductId(updatedProduct.getId());
        if (inventory == null) {
            inventory = new DomainInventoryEntity();
            inventory.setProductId(updatedProduct.getId());
        }
        inventory.setAvailableQuantity(product.getStock_quantity());
        inventoryRepository.save(inventory);
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
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateProductWithRollback(DomainProductEntity product) {
        try {
            updateProduct(product);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update product", e);
        }
    }

    private void notifyProductUpdate(DomainProductEntity product) {
        kafkaProducer.produceUpdateMessage(product);
    }
}