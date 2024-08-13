package domain;

import java.util.UUID;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ai.shreds.adapter.AdapterCreateProductRequest;
import ai.shreds.adapter.AdapterUpdateProductRequest;
import ai.shreds.adapter.AdapterProductMapper;
import ai.shreds.domain.DomainProductRepositoryPort;
import ai.shreds.domain.DomainInventoryServicePort;
import ai.shreds.domain.DomainProductEntity;

@Service
public class DomainProductService {

    private final DomainProductRepositoryPort productRepository;
    private final DomainInventoryServicePort inventoryService;

    @Autowired
    public DomainProductService(DomainProductRepositoryPort productRepository, DomainInventoryServicePort inventoryService) {
        this.productRepository = productRepository;
        this.inventoryService = inventoryService;
    }

    @Transactional
    public DomainProductEntity createProduct(AdapterCreateProductRequest request) {
        DomainProductEntity product = AdapterProductMapper.INSTANCE.mapToDomainEntity(request);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product);
        inventoryService.updateInventory(product);
        return product;
    }

    @Transactional
    public DomainProductEntity updateProduct(UUID id, AdapterUpdateProductRequest request) {
        DomainProductEntity product = productRepository.findById(id);
        if (product != null) {
            product.setName(request.getName());
            product.setDescription(request.getDescription());
            product.setPrice(request.getPrice());
            product.setCategoryId(request.getCategoryId());
            product.setStockQuantity(request.getStockQuantity());
            product.setUpdatedAt(LocalDateTime.now());
            productRepository.save(product);
            inventoryService.updateInventory(product);
        }
        return product;
    }

    @Transactional
    public void deleteProduct(UUID id) {
        DomainProductEntity product = productRepository.findById(id);
        productRepository.deleteById(id);
        inventoryService.updateInventory(product);
    }
}
