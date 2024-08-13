package ai.shreds.adapter;

import ai.shreds.application.ApplicationCreateProductInputPort;
import ai.shreds.application.ApplicationUpdateProductInputPort;
import ai.shreds.application.ApplicationDeleteProductInputPort;
import ai.shreds.domain.DomainProductEntity;
import ai.shreds.adapter.AdapterCreateProductRequest;
import ai.shreds.adapter.AdapterUpdateProductRequest;
import ai.shreds.adapter.AdapterProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class AdapterProductController {

    private static final Logger logger = LoggerFactory.getLogger(AdapterProductController.class);

    private final ApplicationCreateProductInputPort createProductPort;
    private final ApplicationUpdateProductInputPort updateProductPort;
    private final ApplicationDeleteProductInputPort deleteProductPort;
    private final AdapterProductMapper productMapper;

    @Autowired
    public AdapterProductController(ApplicationCreateProductInputPort createProductPort,
                                    ApplicationUpdateProductInputPort updateProductPort,
                                    ApplicationDeleteProductInputPort deleteProductPort,
                                    AdapterProductMapper productMapper) {
        this.createProductPort = createProductPort;
        this.updateProductPort = updateProductPort;
        this.deleteProductPort = deleteProductPort;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseEntity<AdapterProductResponse> createProduct(@Valid @RequestBody AdapterCreateProductRequest request) {
        try {
            logger.info("Creating product with name: {}", request.getName());
            AdapterProductResponse createdProduct = createProductPort.createProduct(request);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error creating product", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdapterProductResponse> updateProduct(@PathVariable UUID id, @Valid @RequestBody AdapterUpdateProductRequest request) {
        try {
            logger.info("Updating product with id: {}", id);
            AdapterProductResponse updatedProduct = updateProductPort.updateProduct(id, request);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error updating product with id: {}", id, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        try {
            logger.info("Deleting product with id: {}", id);
            deleteProductPort.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("Error deleting product with id: {}", id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}